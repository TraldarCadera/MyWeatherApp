package com.example.myweatherapp.model.rest_entities

import com.google.gson.annotations.SerializedName

data class FactDTO(
    val temp: Int?,

    @SerializedName("feels_like")
    val feelsLike: Int?,

    val pressure_mm: Int?,
    val condition: String?
)
