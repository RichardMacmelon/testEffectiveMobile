package com.example.effectivemobile.data.repository

import com.example.effectivemobile.data.dao.СoursesDao
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class DbRepository  @Inject constructor(
    private val coursesDao: СoursesDao
){

    fun getDbInfo() : СoursesDao {
        return coursesDao
    }
}