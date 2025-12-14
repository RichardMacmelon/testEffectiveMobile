package com.example.effectivemobile.domain.dbUseCase

import com.example.effectivemobile.data.repository.MainRepository
import com.example.effectivemobile.data.tables.СoursesDb
import javax.inject.Inject

class ToggleLikeUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend fun toggleLike(coursesId: Int) {
        mainRepository.getDbItems().toggleLike(coursesId)
    }
}