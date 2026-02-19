package com.example.task_2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class Post(
    val userId: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val body: String
)