package com.example.gifphy.networks.service

import com.example.gifphy.networks.models.GifModel
import com.example.gifphy.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query



interface GifService {

    @GET("gifs/trending")
    suspend fun getGifs(
        @Query("api_key") apiKey: String = API_KEY,
    ): Response<GifModel>
}