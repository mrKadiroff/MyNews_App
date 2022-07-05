package com.example.news_app.ui.mainUi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.news_app.App
import com.example.news_app.R
import com.example.news_app.database.Db
import com.example.news_app.databinding.ActivityBottomnavBinding
import com.example.news_app.utils.Status
import com.example.news_app.viewmodels.MainViewModel
import com.example.news_app.viewmodels.MainViewModelFactory
import javax.inject.Inject

class BottomnavActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var db: Db

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    private val TAG = "BottomnavActivity"
    lateinit var navController: NavController
    lateinit var binding: ActivityBottomnavBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomnavBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupBottomNav()

    }

    private fun setupBottomNav() {
        navController = findNavController(R.id.hostFragment)
        binding.bottomNavigation.setupWithNavController(navController)
    }
}