package com.example.effectivemobile.data.dto

import com.example.effectivemobile.entity.entityForApi.EntityForCourse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EntityForCourseDto(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "text") val text: String,
    @Json(name = "price") val price: String,
    @Json(name = "rate") val rate: String,
    @Json(name = "startDate") val startDate: String,
    @Json(name = "hasLike") val hasLike: Boolean,
    @Json(name = "publishDate") val publishDate: String
)
