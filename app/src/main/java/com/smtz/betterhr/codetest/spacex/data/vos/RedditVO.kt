package com.smtz.betterhr.codetest.spacex.data.vos

import com.google.gson.annotations.SerializedName

data class RedditVO (

    @SerializedName("campaign")
    val campaign: String?,

    @SerializedName("launch")
    val launch: String?,

    @SerializedName("media")
    val media: String?,

    @SerializedName("recovery")
    val recovery: String?
)