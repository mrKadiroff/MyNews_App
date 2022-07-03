package com.example.news_app.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news_app.database.Db
import com.example.news_app.database.CategoryEntity
import com.example.news_app.retrofit.FakerAPI
import com.example.news_app.retrofit.models.SearchResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val fakerAPI: FakerAPI, private val appDatabase: Db) {

    private val _products2 = MutableLiveData<List<CategoryEntity>>()
    val products2: LiveData<List<CategoryEntity>>
        get() = _products2


    suspend fun getDb(){
        val list = ArrayList<CategoryEntity>()
        val product = CategoryEntity(1,"Shoxrux",true)
        val product1 = CategoryEntity(1,"Farrux",true)
        list.add(product)
        list.add(product1)
        appDatabase.categoryDao().addAll(list)
        _products2.postValue(list)
    }



    // from api

    private val _products = MutableLiveData<SearchResult>()
    val products: LiveData<SearchResult>
        get() = _products


    suspend fun getProducts(word:String){
        val result = fakerAPI.getProducts(word)
        if(result.isSuccessful && result.body() != null){
            _products.postValue(result.body())
        }
    }





    //experiemnt api

    suspend fun getStandings(name:String) = fakerAPI.getProducts(name)


    suspend fun getCategory(name:String) = fakerAPI.getByCategory(name)

}