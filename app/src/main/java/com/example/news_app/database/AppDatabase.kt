package com.example.news_app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dagger.Provides

@Database(entities = [CategoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
}