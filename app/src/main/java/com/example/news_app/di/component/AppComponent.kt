package com.example.news_app.di.component

import com.example.news_app.ui.OnboardActivity
import com.example.news_app.ui.mainUi.BottomnavActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent {

    fun inject(bottomnavActivity: BottomnavActivity)

}