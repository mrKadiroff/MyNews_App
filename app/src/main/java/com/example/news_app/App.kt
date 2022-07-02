package com.example.news_app

import android.app.Application
import com.example.news_app.di.component.AppComponent
import com.example.news_app.di.component.DaggerAppComponent

class App:Application() {


    companion object {
        lateinit var appComponent: AppComponent

    }


    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(this)
    }

}