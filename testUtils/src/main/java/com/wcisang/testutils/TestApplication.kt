package com.wcisang.testutils

import android.app.Application
import org.koin.core.context.startKoin

class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setTheme(R.style.Theme_GistChallenge)
        startKoin {
            modules(emptyList())
        }
    }
}