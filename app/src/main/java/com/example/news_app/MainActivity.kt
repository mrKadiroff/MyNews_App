package com.example.news_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.news_app.database.Db
import com.example.news_app.ui.OnboardActivity
import com.example.news_app.ui.adapters.onboard.WelcomeFragment
import com.example.news_app.ui.mainUi.BottomnavActivity
import com.example.news_app.viewmodels.MainViewModel
import com.example.news_app.viewmodels.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    @Inject
    lateinit var db: Db

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.appComponent.inject(this)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        val dbCategories = db.categoryDao().getCategoriesByBoolean(true)
        if (dbCategories.isEmpty()){
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, OnboardActivity::class.java)
                startActivity(intent)
                finish()
            },2000)
        }else{
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, BottomnavActivity::class.java)
                startActivity(intent)
                finish()
            },2000)
        }




    }
}