package com.example.effectivemobile.domain.dbUseCase

import com.example.effectivemobile.data.repository.MainRepository
import javax.inject.Inject

class DeleteCourseUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend fun delete(id: Int) {
        mainRepository.getDbItems().deleteCourseById(id)
    }
}