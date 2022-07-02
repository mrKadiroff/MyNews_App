package com.example.news_app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {

    @Insert
    suspend fun addCategory(categoryEntity: CategoryEntity)

    @Query("select * from categoryentity")
    suspend fun getCategories(): List<CategoryEntity>

    @Insert
    suspend fun addAll(list: List<CategoryEntity>)
}