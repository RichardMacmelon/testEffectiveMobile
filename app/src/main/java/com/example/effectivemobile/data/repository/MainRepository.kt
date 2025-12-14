package com.example.effectivemobile.data.repository

import com.example.effectivemobile.data.dao.СoursesDao
import com.example.effectivemobile.data.dto.EntityForCourseDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val apiRepository: ApiRepository,
    private val dbRepository: DbRepository
) {

    fun getItems(): List<EntityForCourseDto> {
        return apiRepository.getItems()
    }

    fun getDbItems() : СoursesDao {
        return dbRepository.getDbInfo()
    }
}