package com.tictactoe.mytest

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyTestApplication : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }
}
