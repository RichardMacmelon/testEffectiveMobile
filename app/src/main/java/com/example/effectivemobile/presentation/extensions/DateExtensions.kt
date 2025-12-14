package com.example.effectivemobile.presentation.extensions

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.toReadableDate(): String {
    return try {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val outputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("ru"))
        val date = LocalDate.parse(this, inputFormatter)
        date.format(outputFormatter)
    } catch (e: Exception) {
        this
    }
}