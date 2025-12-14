package com.example.effectivemobile.presentation.registration

data class LoginFormState(
    val email: String = "",
    val password: String = "",
    val isButtonEnabled: Boolean = false
)
