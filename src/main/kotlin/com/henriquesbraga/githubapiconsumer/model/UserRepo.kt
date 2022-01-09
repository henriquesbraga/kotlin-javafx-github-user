package com.henriquesbraga.githubapiconsumer.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class UserRepo(
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("html_url")
    val url: String
)
