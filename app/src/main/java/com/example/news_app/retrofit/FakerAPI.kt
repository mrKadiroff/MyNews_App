package com.example.news_app.retrofit


import com.example.news_app.retrofit.models.SearchResult
import com.example.news_app.ui.adapters.onboard.WelcomeFragment
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FakerAPI {



    @GET("everything")
    suspend fun getProducts(
        @Query("q") q:String,
        @Query("language") language:String,
        @Query("apiKey") apiKey:String = "16e1b529b13e47fea83f887a4d37bb78"
    ) : Response<SearchResult>


    @GET("top-headlines")
    suspend fun getByCategory(
        @Query("category") category:String,
        @Query("country") country:String = "us",
        @Query("apiKey") apiKey:String = "16e1b529b13e47fea83f887a4d37bb78"
    ) : Response<SearchResult>
}