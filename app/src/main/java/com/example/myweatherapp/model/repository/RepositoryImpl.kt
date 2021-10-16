package com.example.myweatherapp.model.repository

import com.example.myweatherapp.model.entities.Weather
import com.example.myweatherapp.model.entities.getRussianCities
import com.example.myweatherapp.model.entities.getWorldCities

class RepositoryImpl : Repository {

    override fun getWeatherFromServer() = Weather()

    override fun getWeatherFromLocalStorageRus() = getRussianCities()

    override fun getWeatherFromLocalStorageWorld() = getWorldCities()
}