package com.yz.presentation.screen.videoslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.yz.presentation.databinding.ScreenVideoListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class VideosListScreen : Fragment() {

    val viewModel by viewModels<VideosListViewModel>()

    @Inject
    lateinit var adapter: VideosListAdapter

    private var _binding: ScreenVideoListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ScreenVideoListBinding.inflate(inflater, container, false)
        binding.videos.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.stateFlow.collect { state ->
                if (state.videos.isNotEmpty()) {
                    adapter.submitList(state.videos)
                }
            }
        }
    }

}