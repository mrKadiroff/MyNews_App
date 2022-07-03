package com.example.news_app.di.component

import android.content.Context
import com.example.news_app.di.ViewModelModule
import com.example.news_app.di.module.DatabaseModule
import com.example.news_app.di.module.NetworkModule
import com.example.news_app.ui.OnboardActivity
import com.example.news_app.ui.adapters.onboard.WelcomeFragment
import com.example.news_app.ui.mainUi.BottomnavActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,DatabaseModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(bottomnavActivity: BottomnavActivity)

    fun injectWelcome(welcomeFragment: WelcomeFragment)


    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : AppComponent
    }

}