package com.example.news_app.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app.database.CategoryEntity
import com.example.news_app.repositories.NewsRepository
import com.example.news_app.retrofit.models.SearchResult
import com.example.news_app.utils.NetworkHelper
import com.example.news_app.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel  @Inject constructor(private val repository: NewsRepository,
                                         private val randomize: Randomize
) : ViewModel() {

    var wordim:String =""
    private val liveSearchsData = MutableLiveData<Resource<SearchResult>>()
    private val liveCategoryData = MutableLiveData<Resource<SearchResult>>()

    val productsLiveData : LiveData<List<CategoryEntity>>
        get() = repository.products2


    init {
        viewModelScope.launch {
            repository.getDb()
        }
    }



    // from api

    fun getWord(word:String): LiveData<Resource<SearchResult>> {

        viewModelScope.launch {
            liveSearchsData.postValue(Resource.loading(null))
            try {
                coroutineScope {

                    val async1 = async { repository.getStandings(word) }

                    val await1 = async1.await()
                    liveSearchsData.postValue(Resource.success(await1.body()))

                }
            }catch (e:Exception){
                liveSearchsData.postValue(Resource.error(e.message ?: "Error",null))
            }
        }
        return liveSearchsData
    }




    fun getCategory(word:String): LiveData<Resource<SearchResult>> {

        viewModelScope.launch {
            liveCategoryData.postValue(Resource.loading(null))
            try {
                coroutineScope {

                    val async1 = async { repository.getCategory(word) }

                    val await1 = async1.await()
                    liveCategoryData.postValue(Resource.success(await1.body()))

                }
            }catch (e:Exception){
                liveCategoryData.postValue(Resource.error(e.message ?: "Error",null))
            }
        }
        return liveCategoryData
    }


//    val productsLiveData2 : LiveData<SearchResult>
//        get() = repository.products
//
//    init {
//        viewModelScope.launch {
//            repository.getProducts(wordim)
//        }
//    }



}





    class Randomize @Inject constructor(){
        fun doAction(){
            Log.d("CHEEZYCODE", "Random Action")
        }
}