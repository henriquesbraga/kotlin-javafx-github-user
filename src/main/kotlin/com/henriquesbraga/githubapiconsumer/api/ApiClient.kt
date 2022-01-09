package com.henriquesbraga.githubapiconsumer.api

import retrofit2.Retrofit
import okhttp3.MediaType
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


class ApiClient {

    companion object {
        private const val BASE_URL = "https://api.github.com/"
        private lateinit var apiInterface: ApiInterface

        //private val contentType: MediaType = MediaType.parse("application/json")!!
        fun getApiClient(): ApiInterface {
            if(!Companion::apiInterface.isInitialized){
                apiInterface = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiInterface::class.java)
            }
            return apiInterface
        }
    }
}

