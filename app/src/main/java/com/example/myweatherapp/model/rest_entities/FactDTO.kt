package com.example.myweatherapp.model.rest_entities

data class FactDTO(
    val temp: Int?,
    val feels_like: Int?,
    val pressure_mm: Int?,
    val condition: String?
)
