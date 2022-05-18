package com.example.mypurse

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyPurseApp : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}