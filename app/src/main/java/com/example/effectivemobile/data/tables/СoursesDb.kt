package com.example.effectivemobile.data.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.effectivemobile.entity.entityForDb.EntityDb
import java.time.LocalDate

@Entity(tableName = "coursesDb")
data class СoursesDb(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    override val id: Int = 0,

    @ColumnInfo("title")
    override val title: String,

    @ColumnInfo("description")
    override val description: String,

    @ColumnInfo("price")
    override val price: String,

    @ColumnInfo("rate")
    override val rate: String,

    @ColumnInfo("date")
    override val date: String,

    @ColumnInfo("hasLike")
    override val hasLike: Boolean
) : EntityDb