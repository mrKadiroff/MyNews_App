package com.example.news_app.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app.database.CategoryEntity
import com.example.news_app.repositories.NewsRepository
import com.example.news_app.utils.NetworkHelper
import com.example.news_app.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel  @Inject constructor(private val repository: NewsRepository,
                                         private val randomize: Randomize
) : ViewModel() {

    val productsLiveData : LiveData<List<CategoryEntity>>
        get() = repository.products2


    init {
        viewModelScope.launch {
            repository.getDb()
        }
    }




}





    class Randomize @Inject constructor(){
        fun doAction(){
            Log.d("CHEEZYCODE", "Random Action")
        }
}