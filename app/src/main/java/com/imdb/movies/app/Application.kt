package com.imdb.movies.app

import android.app.Application
import com.imdb.feature.listing.di.HomeModule
import com.imdb.movies.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            koin.loadModules(
                listOf(
                    NetworkModule.networkModule,
                    HomeModule.modules
                )
            )
        }
    }
}