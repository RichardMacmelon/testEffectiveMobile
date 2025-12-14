package com.example.effectivemobile.data.api

import android.content.Context
import com.example.effectivemobile.data.dto.CoursesResponseDto
import com.example.effectivemobile.data.dto.EntityForCourseDto
import com.squareup.moshi.Moshi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RetrofitSource @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val moshi: Moshi = Moshi.Builder().build()
    private val adapter = moshi.adapter(CoursesResponseDto::class.java)

    private fun readJsonFromAssets(fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    fun getItems(): List<EntityForCourseDto> {
        val jsonString = readJsonFromAssets("courses.json")
        val response = adapter.fromJson(jsonString)
        return response?.courses ?: emptyList()
    }

}