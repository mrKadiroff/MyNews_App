package com.example.news_app

import android.app.Application
import com.example.news_app.di.component.AppComponent

class App:Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

}