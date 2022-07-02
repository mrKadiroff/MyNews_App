package com.example.news_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import com.example.news_app.ui.OnboardActivity
import com.example.news_app.ui.mainUi.BottomnavActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, BottomnavActivity::class.java)
            startActivity(intent)
            finish()
        },2000)


    }
}