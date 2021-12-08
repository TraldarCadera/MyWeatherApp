package com.example.myweatherapp.model.repository.contentprovider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.myweatherapp.model.database.Database
import com.example.myweatherapp.model.database.HistoryEntity

class CustomContentProvider : ContentProvider() {

    private val URI_ALL = 1
    private val URI_ID = 2
    private val ENTITY_PATH = "HistoryEntity"
    private var authorities: String? = null
    private lateinit var uriMatcher: UriMatcher
    private var entityContentType: String? = null
    private var entityContentItemType: String? = null
    private lateinit var contentUri: Uri

    override fun onCreate(): Boolean {
        authorities = "geekbrains.provider"
        uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        uriMatcher.addURI(authorities, ENTITY_PATH, URI_ALL)
        uriMatcher.addURI(authorities, "$ENTITY_PATH/#", URI_ID)
        entityContentType = "vnd.android.cursor.dir/vnd.$authorities.$ENTITY_PATH"
        entityContentItemType = "vnd.android.cursor.item/vnd.$authorities.$ENTITY_PATH"
        contentUri = Uri.parse("content://$authorities/$ENTITY_PATH")

        return true
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        require(uriMatcher.match(uri) == URI_ID) { "Wrong URI: $uri" }
        val historyDao = Database.db.historyDao()
        val id = ContentUris.parseId(uri)
        historyDao.deleteById(id)
        context?.contentResolver?.notifyChange(uri, null)

        return 1
    }

    override fun getType(uri: Uri): String? {
        when (uriMatcher.match(uri)) {
            URI_ALL -> return entityContentType
            URI_ID -> return entityContentItemType
        }
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri {
        require(uriMatcher.match(uri) == URI_ALL) { "Wrong URI: $uri" }
        val historyDao = Database.db.historyDao()
        val entity = map(values)
        val id: Long = entity.id
        historyDao.insert(entity)
        val resultUri = ContentUris.withAppendedId(contentUri, id)
        context?.contentResolver?.notifyChange(resultUri, null)

        return resultUri
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor {
        val historyDao = Database.db.historyDao()
        val cursor = when (uriMatcher.match(uri)) {
            URI_ALL -> historyDao.getHistoryCursor()
            URI_ID -> {
                val id = ContentUris.parseId(uri)
                historyDao.getHistoryCursor(id)
            }
            else -> throw IllegalArgumentException("Wrong URI: $uri")
        }
        cursor.setNotificationUri(context!!.contentResolver, contentUri)
        return cursor
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        require(uriMatcher.match(uri) == URI_ID) { "Wrong URI: $uri" }
        val historyDao = Database.db.historyDao()
        historyDao.update(map(values))
        context!!.contentResolver.notifyChange(uri, null)
        return 1
    }


    private fun map(values: ContentValues?): HistoryEntity {
        return if (values == null) {
            HistoryEntity()
        } else {
            val id =
                if (values.containsKey(HistoryEntity.ID)) values[HistoryEntity.ID] as Long else 0
            val city = values[HistoryEntity.CITY] as String
            val temperature = values[HistoryEntity.TEMPERATURE] as Int
            HistoryEntity(id, city, temperature)
        }
    }
}