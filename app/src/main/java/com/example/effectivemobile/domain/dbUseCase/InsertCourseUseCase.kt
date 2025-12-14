package com.example.effectivemobile.domain.dbUseCase

import com.example.effectivemobile.data.repository.MainRepository
import com.example.effectivemobile.data.tables.СoursesDb
import javax.inject.Inject


class InsertCourseUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend fun insertCourse(coursesDb: СoursesDb) {
        mainRepository.getDbItems().insertCourse(coursesDb)
    }
}