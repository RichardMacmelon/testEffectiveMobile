package com.example.effectivemobile.data.repository

import android.content.Context
import com.example.effectivemobile.data.api.RetrofitSource
import com.example.effectivemobile.data.dto.EntityForCourseDto
import jakarta.inject.Singleton
import javax.inject.Inject

@Singleton
class ApiRepository @Inject constructor(
    private val retrofitSource: RetrofitSource
) {

    fun getItems(): List<EntityForCourseDto> {
        return retrofitSource.getItems()
    }

}