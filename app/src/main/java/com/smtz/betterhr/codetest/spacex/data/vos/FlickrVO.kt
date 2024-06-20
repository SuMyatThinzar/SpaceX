package com.smtz.betterhr.codetest.spacex.data.vos

import com.google.gson.annotations.SerializedName

data class FlickrVO (

    @SerializedName("small")
    val small: List<String>?,

    @SerializedName("original")
    val original: List<String>?,

    @SerializedName("large")
    val large: List<String>?   // for launchpad image only


)