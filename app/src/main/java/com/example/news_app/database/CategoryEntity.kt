package com.example.news_app.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val selected: Boolean
)