package com.example.myweatherapp.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val city: String = "",
    val temperature: Int = 0,
    val condition: String = ""
) {
    companion object {
        const val ID = "id"
        const val CITY = "city"
//        const val CONDITION = "condition"
        const val TEMPERATURE = "temperature"
    }
}