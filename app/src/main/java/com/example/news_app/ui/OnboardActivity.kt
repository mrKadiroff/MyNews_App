package com.example.news_app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.news_app.App
import com.example.news_app.R
import com.example.news_app.database.Db
import com.example.news_app.databinding.ActivityOnboardBinding
import com.example.news_app.ui.adapters.ViewPagerAdapter
import com.example.news_app.viewmodels.MainViewModel
import com.example.news_app.viewmodels.MainViewModelFactory
import javax.inject.Inject

class OnboardActivity : AppCompatActivity() {


    lateinit var binding: ActivityOnboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }



}