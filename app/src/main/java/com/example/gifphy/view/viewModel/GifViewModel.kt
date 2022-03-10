package com.example.gifphy.view.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gifphy.networks.models.GifModel
import com.example.gifphy.networks.repository.GifRepository
import com.example.gifphy.utils.Resource
import com.example.gifphy.view.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GifViewModel @Inject constructor(
    private val userRepository: GifRepository
) : ViewModel() {

    private var _gifs: MutableLiveData<Resource<GifModel>> = MutableLiveData()
    val gifs: LiveData<Resource<GifModel>> get() = _gifs


    init {
        getUsers()
    }


    private fun getUsers() {
        _gifs.value = Resource.Loading
        viewModelScope.launch {
            val response = userRepository.getAllGifs()
            Log.d(TAG, "here is the response -> $response")
            _gifs.postValue(response)
        }
    }

}