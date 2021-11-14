package com.yz.presentation.screen.videoslist

import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.yz.domain.model.Post
import com.yz.domain.usecase.GetPostsUseCase
import com.yz.presentation.navigation.Navigator
import com.yz.presentation.screen.profile.ProfileScreen
import com.yz.presentation.screen.profile.ProfileScreenData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class VideosListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPostsUseCase: GetPostsUseCase,
    private val navigator: Navigator
) : ViewModel() {

    val stateFlow: Flow<VideosListState> = flow {
        emit(VideosListState(isLoading = true))
        getPostsUseCase()
            .onSuccess { posts ->
                emit(VideosListState(posts = posts))
            }
            .onFailure { exception ->
                emit(VideosListState(error = exception.localizedMessage ?: ""))
            }
    }

    fun onItemClicked(current: Fragment, item: Post) {
        navigator.replaceToScreen(current, ProfileScreen(), ProfileScreenData(item.user.name))
    }

}