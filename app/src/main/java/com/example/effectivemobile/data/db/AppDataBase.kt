package com.example.effectivemobile.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.effectivemobile.data.dao.СoursesDao
import com.example.effectivemobile.data.tables.СoursesDb

@Database(entities = [СoursesDb::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun coursesDao(): СoursesDao
}