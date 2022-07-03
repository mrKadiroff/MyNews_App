package com.example.news_app.retrofit.models

data class SearchResult(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)