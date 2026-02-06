package com.example.task_2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.task_2.model.Post

@Database(entities = [Post::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}
