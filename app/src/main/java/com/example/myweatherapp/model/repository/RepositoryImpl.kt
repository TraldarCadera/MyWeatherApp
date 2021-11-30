package com.example.myweatherapp.model.repository

import com.example.myweatherapp.model.database.Database
import com.example.myweatherapp.model.database.HistoryEntity
import com.example.myweatherapp.model.entities.City
import com.example.myweatherapp.model.entities.Weather
import com.example.myweatherapp.model.rest.WeatherRepo

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(lat: Double, lon: Double): Weather {
        val dto = WeatherRepo.api.getWeather(lat, lon).execute().body()
        return Weather(
            temperature = dto?.fact?.temp ?: 0,
            feelsLike = dto?.fact?.feelsLike ?: 0,
            pressure = dto?.fact?.pressure_mm ?: 0,
            condition = dto?.fact?.condition
        )
    }

    override fun getWeatherFromLocalStorageRus() = City.getRussianCities()

    override fun getWeatherFromLocalStorageWorld() = City.getWorldCities()

    override fun getAllHistory(): List<Weather> {
        return convertHistoryEntityToWeather(Database.db.historyDao().all())
    }

    override fun saveEntity(weather: Weather) {
        Database.db.historyDao().insert(convertWeatherToEntity(weather))
    }

    private fun convertHistoryEntityToWeather(entityList: List<HistoryEntity>): List<Weather> {
        return entityList.map {
            Weather(City(it.city, 0.0, 0.0), it.temperature, 0, it.condition)
        }
    }

    private fun convertWeatherToEntity(weather: Weather): HistoryEntity {
        return HistoryEntity(
            0, weather.city.city,
            weather.temperature,
            weather.condition ?: ""
        )
    }

}