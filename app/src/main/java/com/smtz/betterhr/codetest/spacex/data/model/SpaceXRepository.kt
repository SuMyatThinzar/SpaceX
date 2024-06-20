package com.smtz.betterhr.codetest.spacex.data.model

import com.smtz.betterhr.codetest.spacex.data.vos.LaunchVO
import com.smtz.betterhr.codetest.spacex.data.vos.LaunchpadVO
import com.smtz.betterhr.codetest.spacex.data.vos.PayloadVO
import com.smtz.betterhr.codetest.spacex.data.vos.RocketVO

interface SpaceXRepository {

    fun getLaunchList(
        onSuccess : (List<LaunchVO>) -> Unit,
        onFailure : (String) -> Unit,
    )

    fun getPayload(
        id : String,
        onSuccess : (PayloadVO) -> Unit,
        onFailure : (String) -> Unit,
    )

    fun getLaunchpad(
        id : String,
        onSuccess : (LaunchpadVO) -> Unit,
        onFailure : (String) -> Unit,
    )

    fun getRocket(
        id : String,
        onSuccess : (RocketVO) -> Unit,
        onFailure : (String) -> Unit,
    )
}