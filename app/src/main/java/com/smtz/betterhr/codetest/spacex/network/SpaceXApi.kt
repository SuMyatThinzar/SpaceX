package com.smtz.betterhr.codetest.spacex.network

import com.smtz.betterhr.codetest.spacex.data.vos.LaunchVO
import com.smtz.betterhr.codetest.spacex.utils.API_GET_LAUNCHES
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface SpaceXApi {

    @GET(API_GET_LAUNCHES)
    fun getLaunchList() : Observable<List<LaunchVO>>
}