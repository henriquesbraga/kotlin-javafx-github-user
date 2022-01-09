package com.henriquesbraga.githubapiconsumer.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User (

    @Expose
    @SerializedName("name")
    val username: String?,

    @Expose
    @SerializedName("company")
    val company: String?,

    @Expose
    @SerializedName("bio")
    val bio: String?,

    @Expose
    @SerializedName("followers")
    val usrFollowers: Int?,

    @Expose
    @SerializedName("public_repos")
    val usrRepositories: Int?,

    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String?
)
