package com.example.effectivemobile.entity.entityForApi

import com.example.effectivemobile.data.dto.EntityForCourseDto

interface EntityCoursesResponse {
    val courses: List<EntityForCourseDto>
}