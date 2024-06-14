package com.smtz.betterhr.codetest.spacex.data.vos

import com.google.gson.annotations.SerializedName
import com.smtz.betterhr.codetest.spacex.data.vos.LinkVO

data class LaunchVO (

    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val missionName: String?,

    @SerializedName("date_utc")
    val launchDateUtc: String?,

    @SerializedName("links")
    val links: LinkVO?,

    @SerializedName("details")
    val details: String?,

    @SerializedName("failures")
    val failures: List<FailureVO>?,

)