package com.smtz.betterhr.codetest.spacex.network

import com.smtz.betterhr.codetest.spacex.data.vos.LaunchVO
import com.smtz.betterhr.codetest.spacex.data.vos.PayloadVO
import com.smtz.betterhr.codetest.spacex.utils.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceXApi {

    @GET(API_GET_LAUNCHES)
    fun getLaunchList() : Observable<List<LaunchVO>>

    @GET("$API_GET_PAYLOADS/{id}")
    fun getRelatedPayload(
        @Path("id") payloadId: String,
    ): Observable<PayloadVO>
}