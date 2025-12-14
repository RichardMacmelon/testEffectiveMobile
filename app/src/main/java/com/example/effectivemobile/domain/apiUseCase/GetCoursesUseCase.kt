package com.example.effectivemobile.domain.apiUseCase

import com.example.effectivemobile.data.dto.EntityForCourseDto
import com.example.effectivemobile.data.repository.MainRepository
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    fun execute(): List<EntityForCourseDto> {
        return mainRepository.getItems()
    }
}