package com.example.effectivemobile.domain.dbUseCase

import com.example.effectivemobile.data.repository.MainRepository
import com.example.effectivemobile.data.tables.СoursesDb
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLikedCoursesUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    fun getLikedCourses(): Flow<List<СoursesDb>> {
        return mainRepository.getDbItems().getLikedCourses()
    }
}