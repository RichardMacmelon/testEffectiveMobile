package com.example.effectivemobile.domain.dbUseCase

import com.example.effectivemobile.data.repository.MainRepository
import javax.inject.Inject

class GetAllIdsUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend fun getAllIds() : List<Int> {
        return mainRepository.getDbItems().getAllIds()
    }
}