package com.example.news_app.network

import com.example.news_app.network.modelsearch.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {


    // search by title
    @GET("everything")
    fun getDataByTitle(
        @Query("q") q:String = "sex",
        @Query("apiKey") apiKey:String = "16e1b529b13e47fea83f887a4d37bb78"
    ): Call<SearchResult>





}