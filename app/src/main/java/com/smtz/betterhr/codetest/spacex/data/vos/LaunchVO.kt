package com.smtz.betterhr.codetest.spacex.data.vos

import com.google.gson.annotations.SerializedName

data class LaunchVO (

    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("date_utc")
    val launchDateUtc: String?,

    @SerializedName("links")
    val links: LinkVO?,

    @SerializedName("details")
    val details: String?,

    @SerializedName("failures")
    val failures: List<FailureVO>?,

    @SerializedName("payloads")
    val payloads: List<String>?,

)