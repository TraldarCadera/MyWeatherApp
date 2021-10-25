package com.example.myweatherapp.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class BoundService : Service() {

    private val binder: IBinder = ServiceBinder()
    private var fibonacci1: Long = 0
    private var fibonacci2: Long = 1

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    val nextFibonacci: Long
        get() {
            val result = fibonacci1 + fibonacci2
            fibonacci1 = fibonacci2
            fibonacci2 = result
            return result
        }

    internal inner class ServiceBinder : Binder() {
        private val service: BoundService
            get() = this@BoundService
        val nextFibonacci: Long
            get() = service.nextFibonacci
    }
}