package com.example.effectivemobile.presentation.registration

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor() : ViewModel() {

    private val _formState = MutableStateFlow(LoginFormState())
    val formState: StateFlow<LoginFormState> = _formState.asStateFlow()

    private val _openLinkEvent = MutableSharedFlow<String>()
    val openLinkEvent = _openLinkEvent.asSharedFlow()

    private fun isValidEmail(email: String): Boolean {
        val regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        return email.matches(regex)
    }

    fun onEmailChanged(email: String) {
        _formState.update { it.copy(email = email) }
        validateForm()
    }

    fun onPasswordChanged(password: String) {
        _formState.update { it.copy(password = password) }
        validateForm()
    }

    private fun validateForm() {
        val state = _formState.value
        val isValid = state.email.isNotBlank() &&
                state.password.isNotBlank() &&
                isValidEmail(state.email)
        _formState.update { it.copy(isButtonEnabled = isValid) }
    }

    fun getColored(
        fullText: String
    ): SpannableString {

        val spannable = SpannableString(fullText)

        val index = fullText.indexOf('?')
        if (index == -1 || index == fullText.lastIndex) {
            return spannable
        }

        val start = index + 1
        val end = fullText.length

        spannable.setSpan(
            ForegroundColorSpan("#12B956".toColorInt()),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return spannable
    }

    fun onVkClicked() {
        emitLink("https://vk.com/")
    }

    fun onOkClicked() {
        emitLink("https://ok.ru/")
    }

    private fun emitLink(url: String) {
        viewModelScope.launch {
            _openLinkEvent.emit(url)
        }
    }

    fun onLoginClicked(onSuccess: () -> Unit) {
        if (_formState.value.isButtonEnabled) {
            onSuccess()
        }
    }
}