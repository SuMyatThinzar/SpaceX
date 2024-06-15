package com.smtz.betterhr.codetest.spacex.data.vos

import com.google.gson.annotations.SerializedName

data class PayloadVO(

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String?,

    @SerializedName("type")
    val type: String?,

    @SerializedName("customers")
    val customers: List<String>?,

    @SerializedName("manufacturers")
    val manufacturers: List<String>?,

    @SerializedName("mass_kg")
    val massKg: Double?,

    @SerializedName("mass_lbs")
    val massLbs: Double?,

) {

    fun getCustomerListAsCommaSeparatedString() : String? {
        return customers?.joinToString ( ", " )
    }

    fun getManufacturersAsCommaSeparatedString() : String? {
        return manufacturers?.joinToString ( ", " )
    }
}