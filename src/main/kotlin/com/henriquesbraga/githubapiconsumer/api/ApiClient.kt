package com.henriquesbraga.githubapiconsumer.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import okhttp3.MediaType

class ApiClient {

    companion object {
        private const val BASE_URL = "https://api.github.com/"
        private lateinit var apiInterface: ApiInterface

        private val contentType: MediaType = MediaType.parse("application/json")!!
        fun getApiClient(): ApiInterface {
            if(!Companion::apiInterface.isInitialized){
                apiInterface = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(Json{ignoreUnknownKeys = true}.asConverterFactory(contentType))
                    .build().create(ApiInterface::class.java)
            }
            return apiInterface
        }
    }
}

