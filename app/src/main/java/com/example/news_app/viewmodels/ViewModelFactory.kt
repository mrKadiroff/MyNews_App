package com.example.news_app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news_app.repositories.NewsRepository
import com.example.news_app.utils.NetworkHelper
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val newsRepository: NewsRepository,
                       val networkHelper: NetworkHelper
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(newsRepository, networkHelper) as T
        }
        throw IllegalArgumentException("Error")
    }
}