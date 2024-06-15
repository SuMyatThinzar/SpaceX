package com.smtz.betterhr.codetest.spacex.data.model

import com.smtz.betterhr.codetest.spacex.data.vos.LaunchVO
import com.smtz.betterhr.codetest.spacex.data.vos.PayloadVO

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
}