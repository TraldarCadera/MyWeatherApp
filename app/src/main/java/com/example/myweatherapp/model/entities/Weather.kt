package com.example.myweatherapp.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val city: City = City.getDefaultCity(),
    val temperature: Int = 0,
    val feelsLike: Int = 0,
    val condition: String? = "Пока не понятно",
    val pressure: Int? = 0
) : Parcelable







