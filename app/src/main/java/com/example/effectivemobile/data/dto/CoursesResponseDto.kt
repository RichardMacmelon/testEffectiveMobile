package com.example.effectivemobile.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoursesResponseDto(
    @Json(name = "courses") val courses: List<EntityForCourseDto>
)
