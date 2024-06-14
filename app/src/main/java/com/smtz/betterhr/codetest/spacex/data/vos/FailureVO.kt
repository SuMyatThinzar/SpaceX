package com.smtz.betterhr.codetest.spacex.data.vos

import com.google.gson.annotations.SerializedName

data class FailureVO (

    @SerializedName("time")
    val time: Int?,

    @SerializedName("altitude")
    val altitude: Int?,

    @SerializedName("reason")
    val reason: String?,
)