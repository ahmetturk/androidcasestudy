package com.ahmetturk.definex

import android.app.Application
import com.ahmetturk.definex.analytics.Analytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class DefineXApplication : Application() {
    companion object {
        lateinit var Instance: DefineXApplication
    }

    init {
        Instance = this
    }

    val analytics = Analytics()

    override fun onCreate() {
        super.onCreate()
        analytics.initialize(Firebase.analytics)
    }

}