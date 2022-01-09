package com.henriquesbraga.githubapiconsumer.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient {

    companion object {
        private const val BASE_URL = "https://api.github.com/"
        private lateinit var apiInterface: ApiInterface

        fun getApiClient(): ApiInterface {
            if(!Companion::apiInterface.isInitialized){
                apiInterface = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiInterface::class.java)
            }
            return apiInterface
        }
    }
}

