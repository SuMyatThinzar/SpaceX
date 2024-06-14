package com.smtz.betterhr.codetest.spacex.data.vos

import com.google.gson.annotations.SerializedName

data class PatchVO (

    @SerializedName("small")
    val small: String?,

    @SerializedName("large")
    val large: String?
)