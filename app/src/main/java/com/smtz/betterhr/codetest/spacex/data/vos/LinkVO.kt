package com.smtz.betterhr.codetest.spacex.data.vos

import com.google.gson.annotations.SerializedName

data class LinkVO(

    @SerializedName("patch")
    val patch: PatchVO,

    @SerializedName("reddit")
    val reddit: RedditVO?,

    @SerializedName("flickr")
    val flickr: FlickrVO?,

    @SerializedName("webcast")
    val webcast: String?,

    @SerializedName("article")
    val article: String?,

    @SerializedName("wikipedia")
    val wikipedia: String?
)
