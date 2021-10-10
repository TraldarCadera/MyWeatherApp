package com.example.myweatherapp.model.entities

data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 0,
    val feelsLike: Int = 0
)

fun getDefaultCity() = City("Домодедово", 55.437170, 37.767998)

