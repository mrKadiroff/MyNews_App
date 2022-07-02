package com.example.news_app.di.module

import android.content.Context
import androidx.room.Room
import com.example.news_app.database.Db
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideFakerDB(context : Context) : Db{
        return Room.databaseBuilder(context, Db::class.java, "FakerDB").build()
    }

}