package com.example.myweatherapp.model.repository

import com.example.myweatherapp.model.entities.City
import com.example.myweatherapp.model.entities.Weather

class RepositoryImpl : Repository {
    override fun getWeatherFromServer() = Weather()
    override fun getWeatherFromLocalStorageRus() = City.getRussianCities()
    override fun getWeatherFromLocalStorageWorld() = City.getWorldCities()
}