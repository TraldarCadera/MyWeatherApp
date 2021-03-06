package com.example.myweatherapp.di

import com.example.myweatherapp.framework.ui.details.DetailsViewModel
import com.example.myweatherapp.framework.ui.history.HistoryViewModel
import com.example.myweatherapp.framework.ui.main.MainViewModel
import com.example.myweatherapp.model.repository.Repository
import com.example.myweatherapp.model.repository.RepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl() }
    viewModel { MainViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
}