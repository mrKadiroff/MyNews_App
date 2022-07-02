package com.example.news_app.ui.mainUi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.news_app.App
import com.example.news_app.R
import com.example.news_app.databinding.ActivityBottomnavBinding
import com.example.news_app.utils.Status
import com.example.news_app.viewmodels.UserViewModel
import javax.inject.Inject

class BottomnavActivity : AppCompatActivity() {

    @Inject
    lateinit var userViewModel: UserViewModel
    private val TAG = "BottomnavActivity"
    lateinit var binding: ActivityBottomnavBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomnavBinding.inflate(layoutInflater)
        App.appComponent.inject(this)
        setContentView(binding.root)


        userViewModel.getUsers().observe(this, Observer {
            when(it.status){
                Status.LOADING -> {

                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "onCreate: ${it.message}")
                }
                Status.SUCCESS -> {
                    Log.d(TAG, "onCreate: ${it.data}")
                }
            }
        })

    }
}