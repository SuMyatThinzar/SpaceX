package com.smtz.betterhr.codetest.spacex

import android.app.Application
import com.smtz.betterhr.codetest.spacex.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SpaceXApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SpaceXApplication)
            modules(appModule)
        }
    }
}