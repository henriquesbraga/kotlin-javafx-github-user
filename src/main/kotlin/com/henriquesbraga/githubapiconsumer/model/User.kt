package com.henriquesbraga.githubapiconsumer.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable()
data class User(
    @SerialName("name")
    val username: String?,

    @SerialName("company")
    val company: String?,

    @SerialName("bio")
    val bio: String?,

    @SerialName("followers")
    val usrFollowers: Int?,

    @SerialName("public_repos")
    val usrRepositories: Int?,

    @SerialName("avatar_url")
    val avatarUrl: String?
)
