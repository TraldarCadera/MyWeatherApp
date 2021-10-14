package com.example.myweatherapp.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(
    val city: String,
    val lat: Double,
    val lon: Double
) : Parcelable
