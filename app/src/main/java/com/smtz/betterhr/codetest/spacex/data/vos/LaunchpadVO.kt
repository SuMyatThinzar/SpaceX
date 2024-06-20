package com.smtz.betterhr.codetest.spacex.data.vos

import com.google.gson.annotations.SerializedName

data class LaunchpadVO (

    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("full_name")
    val fullName: String?,

    @SerializedName("locality")
    val locality: String?,

    @SerializedName("region")
    val region: String?,

    @SerializedName("latitude")
    val latitude: String?,

    @SerializedName("longitude")
    val longitude: String?,

    @SerializedName("launch_attempts")
    val launchAttempts: String?,

    @SerializedName("launch_successes")
    val launchSuccesses: String?,

    @SerializedName("images")
    val images: FlickrVO?,

    @SerializedName("cost_per_launch")
    val costPerLaunch: Double?,

)