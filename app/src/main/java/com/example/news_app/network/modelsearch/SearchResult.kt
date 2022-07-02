package com.example.news_app.network.modelsearch

data class SearchResult(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)