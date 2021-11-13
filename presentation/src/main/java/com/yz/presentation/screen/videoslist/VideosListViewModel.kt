package com.yz.presentation.screen.videoslist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.yz.domain.usecase.GetVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class VideosListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getVideosUseCase: GetVideosUseCase
) : ViewModel() {

    val stateFlow: Flow<VideosListState> = flow {
        emit(VideosListState(isLoading = true))
        getVideosUseCase()
            .onSuccess { videos ->
                emit(VideosListState(videos = videos))
            }
            .onFailure { exception ->
                emit(VideosListState(error = exception.localizedMessage ?: ""))
            }
    }

}