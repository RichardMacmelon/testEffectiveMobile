package com.example.effectivemobile.data.repository

import android.content.Context
import com.example.effectivemobile.data.api.RetrofitSource
import com.example.effectivemobile.data.dto.EntityForCourseDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val apiRepository: ApiRepository
) {

    fun getItems(): List<EntityForCourseDto> {
        return apiRepository.getItems()
    }
}