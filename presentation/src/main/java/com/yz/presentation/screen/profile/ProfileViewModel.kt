package com.yz.presentation.screen.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.yz.common.BundleKey
import com.yz.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    val stateFlow: Flow<ProfileState> = flow {
        emit(ProfileState(isLoading = true))
        savedStateHandle.get<ProfileScreenData>(BundleKey.ScreenData.name)?.let { data ->
            getUserUseCase(data.userName)
                .onSuccess { user ->
                    emit(ProfileState(user = user))
                }
                .onFailure { exception ->
                    emit(ProfileState(error = exception.localizedMessage ?: ""))
                }
        }
    }

}