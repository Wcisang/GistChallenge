package com.wcisang.gistchallenge.application

import android.app.Application
import com.wcisang.gistchallenge.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GistApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GistApplication)
            modules(appModule)
        }
    }
}