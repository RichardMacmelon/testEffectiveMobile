package com.example.effectivemobile.data.dto

import com.example.effectivemobile.entity.entityForApi.EntityForCourse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EntityForCourseDto(
    @Json(name = "id") override val id: Int,
    @Json(name = "title") override val title: String,
    @Json(name = "text") override val text: String,
    @Json(name = "price") override val price: String,
    @Json(name = "rate") override val rate: String,
    @Json(name = "startDate") override val startDate: String,
    @Json(name = "hasLike") override val hasLike: Boolean,
    @Json(name = "publishDate") override val publishDate: String
) : EntityForCourse
