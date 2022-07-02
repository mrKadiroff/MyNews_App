package com.example.news_app.repositories

import com.example.news_app.database.AppDatabase
import com.example.news_app.database.CategoryEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val appDatabase: AppDatabase) {

    suspend fun getNews() = appDatabase.categoryDao().getCategories()

    suspend fun addNews(categoryEntity: CategoryEntity) = appDatabase.categoryDao().addCategory(categoryEntity)


}