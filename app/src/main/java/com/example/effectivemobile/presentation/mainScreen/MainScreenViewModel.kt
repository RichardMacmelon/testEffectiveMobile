package com.example.effectivemobile.presentation.mainScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobile.data.dto.EntityForCourseDto
import com.example.effectivemobile.domain.apiUseCase.GetCoursesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase
) : ViewModel(){

    private val _items = MutableStateFlow<List<EntityForCourseDto>>(emptyList())
    val items: StateFlow<List<EntityForCourseDto>> = _items

    init {
        test()
    }
        fun test() {
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
}