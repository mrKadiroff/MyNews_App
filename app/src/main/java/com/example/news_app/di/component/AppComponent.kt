package com.example.news_app.di.component

import android.content.Context
import com.example.news_app.MainActivity
import com.example.news_app.di.ViewModelModule
import com.example.news_app.di.module.DatabaseModule
import com.example.news_app.di.module.NetworkModule
import com.example.news_app.ui.OnboardActivity
import com.example.news_app.ui.adapters.onboard.SelectFragment
import com.example.news_app.ui.adapters.onboard.WelcomeFragment
import com.example.news_app.ui.mainUi.BottomnavActivity
import com.example.news_app.ui.mainUi.CategoryTabFragment
import com.example.news_app.ui.mainUi.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,DatabaseModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun injectWelcome(selectFragment: SelectFragment)

    fun injectHome(homeFragment: HomeFragment)

    fun injectCategorytab(categoryTabFragment: CategoryTabFragment)


    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : AppComponent
    }

}