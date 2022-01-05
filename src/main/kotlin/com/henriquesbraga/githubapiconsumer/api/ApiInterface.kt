package com.henriquesbraga.githubapiconsumer.api
import com.henriquesbraga.githubapiconsumer.model.User
import com.henriquesbraga.githubapiconsumer.model.UserRepo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("users/{usrName}")
    suspend fun getUser(@Path("usrName") usrName: String): Response<User>

    @GET("users/{usrName}/repos")
    suspend fun  getRepos(@Path("usrName") usrName: String): Response<List<UserRepo>>

}