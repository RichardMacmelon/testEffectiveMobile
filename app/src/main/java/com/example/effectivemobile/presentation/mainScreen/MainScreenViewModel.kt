package com.example.effectivemobile.presentation.mainScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobile.data.dto.EntityForCourseDto
import com.example.effectivemobile.data.tables.СoursesDb
import com.example.effectivemobile.domain.apiUseCase.GetCoursesUseCase
import com.example.effectivemobile.domain.dbUseCase.DeleteCourseUseCase
import com.example.effectivemobile.domain.dbUseCase.GetAllIdsUseCase
import com.example.effectivemobile.domain.dbUseCase.InsertCourseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val insertCourseUseCase: InsertCourseUseCase,
    private val deleteCourseUseCase: DeleteCourseUseCase,
    private val getAllIdsUseCase: GetAllIdsUseCase
) : ViewModel() {

//    private val _items = MutableStateFlow<List<EntityForCourseDto>>(emptyList())
//    val items: StateFlow<List<EntityForCourseDto>> = _items

    private var listCheckboxStates = emptyList<Int>().toMutableList()

    init {
        getAllCourses()
    }

    private val _isSortedDescending = MutableStateFlow(false)
    val isSortedDescending: StateFlow<Boolean> = _isSortedDescending

    private val _items = MutableStateFlow<List<EntityForCourseDto>>(emptyList())

    val items: StateFlow<List<EntityForCourseDto>> =
        combine(_items, _isSortedDescending) { list, isSorted ->
            if (isSorted) {
                list.sortedByDescending {
                    LocalDate.parse(it.publishDate)
                }
            } else {
                list
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            emptyList()
        )

    fun setItems(list: List<EntityForCourseDto>) {
        _items.value = list
    }

    fun toggleSort() {
        _isSortedDescending.value = !_isSortedDescending.value
    }

    fun getAllCourses() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                getCoursesUseCase.execute()
            }.fold(
                onSuccess = {
                    _items.value = it
                },
                onFailure = {
                    Log.d("MainScreenViewModel", it.message ?: "")
                }
            )
        }
    }

    fun updateLike(coursesDb: СoursesDb, isLiked: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            if (isLiked) {
                insertCourseUseCase.insertCourse(coursesDb)
            } else {
                deleteCourseUseCase.delete(coursesDb.id)
            }
        }
    }

    suspend fun checkBoxState(): MutableList<Int> {
        getAllIdsUseCase.getAllIds().forEach { id ->
            listCheckboxStates.add(id)
        }
        return listCheckboxStates
    }
}




