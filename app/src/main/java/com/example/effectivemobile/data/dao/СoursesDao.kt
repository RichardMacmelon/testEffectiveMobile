package com.example.effectivemobile.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.effectivemobile.data.tables.СoursesDb
import kotlinx.coroutines.flow.Flow

@Dao
interface СoursesDao {

    @Insert
    suspend fun insertCourse(course: СoursesDb)

    @Query("SELECT * FROM coursesDb WHERE hasLike = 1")
    fun getLikedCourses(): Flow<List<СoursesDb>>

    @Query("DELETE FROM coursesDb WHERE id = :courseId")
    suspend fun deleteCourseById(courseId: Int)

    @Query("SELECT id FROM coursesDb")
    suspend fun getAllIds(): List<Int>

}