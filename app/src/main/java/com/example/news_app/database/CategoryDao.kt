package com.example.news_app.database

import androidx.room.*

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCategory(categoryEntity: CategoryEntity)

    @Query("select * from categoryentity")
    suspend fun getCategories(): List<CategoryEntity>

    @Query("select * from categoryentity")
   fun getDbCategories(): List<CategoryEntity>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(list: List<CategoryEntity>)




    @Update
    fun updateCategory(categoryEntity: CategoryEntity)

    @Query("select * from categoryentity where selected=:selected")
    fun getCategoriesByBoolean(selected:Boolean) : List<CategoryEntity>
}