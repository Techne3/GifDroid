package com.example.gifphy.di

import com.example.gifphy.networks.repository.GifRepository
import com.example.gifphy.networks.service.GifService
import com.example.gifphy.utils.BASE_URL
import com.example.gifphy.utils.TIMEOUT
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(Singleton::class)


object AppModule {


    @Singleton
    @Provides
    fun providesMoshi() = Moshi.Builder().build()

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
                .setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder().connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }


    @Provides
    @Singleton
    fun providesRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun providesGifService(retrofit: Retrofit):GifService {
        return retrofit.create(GifService::class.java)

    }
    @Provides
    @Singleton
    fun  providesGifRepository(gitService: GifService):GifRepository{
        return GifRepository(gitService)
    }



}