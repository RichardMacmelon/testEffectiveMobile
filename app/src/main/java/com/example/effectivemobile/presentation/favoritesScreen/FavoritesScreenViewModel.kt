package com.example.effectivemobile.presentation.favoritesScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobile.domain.dbUseCase.DeleteCourseUseCase
import com.example.effectivemobile.domain.dbUseCase.GetLikedCoursesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesScreenViewModel @Inject constructor(
    private val getLikedCoursesUseCase : GetLikedCoursesUseCase,
    private val deleteCourseUseCase: DeleteCourseUseCase
): ViewModel() {

    val allLikeCourses = this.getLikedCoursesUseCase.getLikedCourses().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

    fun deleteCourse(id: Int) {
        viewModelScope.launch {
            deleteCourseUseCase.delete(id)
        }
    }

}