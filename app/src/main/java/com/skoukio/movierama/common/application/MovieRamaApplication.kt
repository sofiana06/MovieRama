package com.skoukio.movierama.common.application

import android.app.Application
import com.skoukio.movierama.network.api.MovieRamaClient
import com.skoukio.movierama.network.providers.NetworkProvider
import timber.log.Timber

class MovieRamaApplication : Application() {

    companion object {
        private lateinit var instance: MovieRamaApplication

        @JvmStatic
        fun get(): MovieRamaApplication {
            return instance
        }
    }

    val networkProvider: NetworkProvider by lazy {
        return@lazy MovieRamaClient()
    }

    override fun onCreate() {
        super.onCreate()
        initTimberLogging()
    }

    private fun initTimberLogging() {
        Timber.plant(Timber.DebugTree())
    }
}