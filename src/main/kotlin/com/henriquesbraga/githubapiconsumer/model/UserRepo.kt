package com.henriquesbraga.githubapiconsumer.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRepo(
    @SerialName("name")
    val name: String,
    @SerialName("html_url")
    val url: String
)
