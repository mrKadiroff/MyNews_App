package com.example.news_app.viewmodels

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

class UserViewModel  @Inject constructor(private val newsRepository: NewsRepository,
                    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val liveData = MutableLiveData<Resource<List<CategoryEntity>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            liveData.postValue(Resource.loading(null))
            val categoryEntity = CategoryEntity(1,"Shoxrux",true)
            newsRepository.addNews(categoryEntity)
        }
    }

    fun getUsers(): LiveData<Resource<List<CategoryEntity>>> = liveData

}