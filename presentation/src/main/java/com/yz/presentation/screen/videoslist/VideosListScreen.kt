package com.yz.presentation.screen.videoslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.yz.domain.model.Post
import com.yz.presentation.databinding.ScreenVideoListBinding
import com.yz.presentation.listener.OnItemClickListener
import com.yz.presentation.screen.videoslist.mediplayer.ExoplayerManager
import com.yz.presentation.screen.videoslist.mediplayer.VideosPlaybackManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VideosListScreen : Fragment() {

    val viewModel by viewModels<VideosListViewModel>()

    lateinit var adapter: VideosListAdapter

    private var _binding: ScreenVideoListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ScreenVideoListBinding.inflate(inflater, container, false)

        adapter = VideosListAdapter(VideosPlaybackManager().apply {
            playerManager = ExoplayerManager(requireContext())
        })

        binding.videos.adapter = adapter

        adapter.setOnItemClickListener(object : OnItemClickListener<Post> {
            override fun onItemClicked(item: Post) {
                viewModel.onItemClicked(this@VideosListScreen, item)
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.stateFlow.collect { state ->
                if (state.posts.isNotEmpty()) {
                    adapter.submitList(state.posts)
                }
            }
        }
        viewLifecycleOwner.getLifecycle().addObserver(adapter.playbackManager);
    }

    override fun onDestroyView() {
        viewLifecycleOwner.getLifecycle().removeObserver(adapter.playbackManager);
        _binding = null
        super.onDestroyView()
    }

}