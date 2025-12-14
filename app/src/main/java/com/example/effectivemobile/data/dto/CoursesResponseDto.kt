package com.example.effectivemobile.data.dto

import com.example.effectivemobile.entity.entityForApi.EntityCoursesResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoursesResponseDto(
    @Json(name = "courses") override val courses: List<EntityForCourseDto>
) : EntityCoursesResponse
