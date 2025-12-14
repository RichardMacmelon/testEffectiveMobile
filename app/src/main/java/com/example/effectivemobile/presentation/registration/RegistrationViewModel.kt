package com.example.effectivemobile.presentation.registration

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor() : ViewModel() {

    private val _formState = MutableStateFlow(LoginFormState())
    val formState: StateFlow<LoginFormState> = _formState.asStateFlow()

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

    fun onLoginClicked(onSuccess: () -> Unit) {
        if (_formState.value.isButtonEnabled) {
            onSuccess()
        }
    }
}