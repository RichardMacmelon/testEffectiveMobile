package com.example.effectivemobile.entity.entityForDb

import java.time.LocalDate

interface EntityDb {
    val id: Int
    val title: String
    val description: String
    val price: String
    val rate: String
    val date: String
    val hasLike: Boolean
}