package com.example.effectivemobile.domain.dbUseCase

import com.example.effectivemobile.data.repository.MainRepository
import javax.inject.Inject

class ExistsByIdUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend fun existsById(courseId: Int) : Boolean {
        return mainRepository.getDbItems().existsById(courseId)
    }
}