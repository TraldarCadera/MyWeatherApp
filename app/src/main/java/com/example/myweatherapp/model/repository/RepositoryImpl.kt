package com.example.myweatherapp.model.repository

import com.example.myweatherapp.model.WeatherLoader
import com.example.myweatherapp.model.entities.City
import com.example.myweatherapp.model.entities.Weather

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(lat: Double, lon: Double): Weather {
        val dto = WeatherLoader.loadWeather(lat, lon)
        return Weather(
            temperature = dto?.fact?.temp ?: 0,
            feelsLike = dto?.fact?.feels_like ?: 0,
            pressure = dto?.fact?.pressure_mm ?: 0,
            condition = dto?.fact?.condition
        )
    }

    override fun getWeatherFromLocalStorageRus() = City.getRussianCities()
    override fun getWeatherFromLocalStorageWorld() = City.getWorldCities()
}