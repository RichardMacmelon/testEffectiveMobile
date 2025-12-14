package com.example.effectivemobile.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.effectivemobile.data.tables.СoursesDb
import kotlinx.coroutines.flow.Flow

@Dao
interface СoursesDao {

    @Query("SELECT * FROM coursesDb")
    fun getAllCourses(): Flow<List<СoursesDb>>

    @Insert
    suspend fun insertCourse(course: СoursesDb)

    @Query("UPDATE coursesDb SET hasLike = NOT hasLike WHERE id = :courseId")
    suspend fun toggleLike(courseId: Int)

    @Query("SELECT * FROM coursesDb WHERE hasLike = 1")
    fun getLikedCourses(): Flow<List<СoursesDb>>

    @Query("SELECT EXISTS(SELECT 1 FROM coursesDb WHERE id = :courseId)")
    suspend fun existsById(courseId: Int): Boolean

    @Query("DELETE FROM coursesDb WHERE id = :courseId")
    suspend fun deleteCourseById(courseId: Int)

    @Query("SELECT id FROM coursesDb")
    suspend fun getAllIds(): List<Int>

}