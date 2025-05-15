package com.igris.styleai.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import styleai.core.app.di.appModule
import styleai.core.app.onStyleAiStarted

class StyleAiApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        onStyleAiStarted()
        startKoin {
            androidContext(this@StyleAiApplication)
            modules(
                appModule()
            )
        }
    }

}


