package com.example.myweatherapp.model.repository

import com.example.myweatherapp.model.entities.Weather

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorage(): Weather
}