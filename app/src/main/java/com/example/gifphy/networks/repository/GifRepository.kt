package com.example.gifphy.networks.repository

import android.util.Log
import com.example.gifphy.networks.models.GifModel
import com.example.gifphy.networks.service.GifService
import com.example.gifphy.utils.Resource
import com.example.gifphy.view.TAG
import java.lang.Exception
import javax.inject.Inject

class GifRepository @Inject constructor(
    private val gifService: GifService
) {

    suspend fun getAllGifs(): Resource<GifModel> {
        return try {
            val response = gifService.getGifs()
            Log.d(TAG, "here is the response in repository -> $response")
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Failed to get All Users.")
            }
        } catch (ex: Exception) {
            Resource.Error("unexpected error")
        }
    }

}