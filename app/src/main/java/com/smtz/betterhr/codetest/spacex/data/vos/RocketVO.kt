package com.smtz.betterhr.codetest.spacex.data.vos

import com.google.gson.annotations.SerializedName

data class RocketVO(

    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("flickr_images")
    val flickrImages: List<String>?,

    @SerializedName("cost_per_launch")
    val costPerLaunch: Double?,

    @SerializedName("success_rate_pct")
    val successRatePct: Double?,

    @SerializedName("first_flight")
    val firstFlight: String?,

    @SerializedName("country")
    val country: String?,

    @SerializedName("company")
    val company: String?,

    @SerializedName("description")
    val description: String?,


    )
