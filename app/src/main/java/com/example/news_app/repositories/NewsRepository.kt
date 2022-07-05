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
        val product = CategoryEntity(1,"entertainment",false)
        val product1 = CategoryEntity(2,"business",false)
        val product3 = CategoryEntity(3,"general",false)
        val product4 = CategoryEntity(4,"health",false)
        val product5 = CategoryEntity(5,"science",false)
        val product6 = CategoryEntity(6,"sports",false)
        val product7 = CategoryEntity(7,"technology",false)
        list.add(product)
        list.add(product1)
        list.add(product3)
        list.add(product4)
        list.add(product5)
        list.add(product6)
        list.add(product7)
        val categories = appDatabase.categoryDao().getCategories()
        if (categories.isEmpty()){
            appDatabase.categoryDao().addAll(list)
            _products2.postValue(list)
        }else{
            _products2.postValue(categories)
        }

    }



    // from api

    private val _products = MutableLiveData<SearchResult>()
    val products: LiveData<SearchResult>
        get() = _products


    suspend fun getProducts(word:String,language:String){
        val result = fakerAPI.getProducts(word,language)
        if(result.isSuccessful && result.body() != null){
            _products.postValue(result.body())
        }
    }





    //experiemnt api

    suspend fun getStandings(name:String,language: String) = fakerAPI.getProducts(name,language)


    suspend fun getCategory(name:String) = fakerAPI.getByCategory(name)

//    suspend fun getByBoolean() = appDatabase.categoryDao().getCategoriesByBoolean(true)

}