package com.example.gifphy.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gifphy.databinding.ItemLayoutBinding
import com.example.gifphy.databinding.RecyclerViewBinding
import com.example.gifphy.networks.models.GifModel
import com.example.gifphy.networks.repository.GifRepository
import com.example.gifphy.utils.Resource
import com.example.gifphy.utils.showToast
import com.example.gifphy.view.adapter.GifAdapter
import com.example.gifphy.view.viewModel.GifViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GifFragment :Fragment() {



    private var _binding: RecyclerViewBinding? = null
    private val binding: RecyclerViewBinding get() = _binding!!

    private val viewModel: GifViewModel by activityViewModels()


    private val gifAdapter by lazy {
       GifAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = RecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            jsonRv.apply {
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL
                    )
                )
            }


            viewModel.gifs.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {

                        jsonRv.adapter = gifAdapter
                        gifAdapter.submitList(response.data as List<GifModel>)
                    }
                    is Resource.Error -> {
                        showToast(response.message)
                    }
                }

            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}