package com.kbcoding.signUp.presentation.domain.entities

data class SignUpData(
    val email: String,
    val username: String,
    val password: String,
    val repeatPassword: String,
)