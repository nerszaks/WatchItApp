package com.yz.presentation.screen.profile

import com.yz.domain.model.User

data class ProfileState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String? = null
)