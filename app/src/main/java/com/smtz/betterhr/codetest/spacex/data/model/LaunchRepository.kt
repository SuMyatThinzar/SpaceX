package com.smtz.betterhr.codetest.spacex.data.model

import com.smtz.betterhr.codetest.spacex.data.vos.LaunchVO

interface LaunchRepository {

    fun getLaunchList(
        onSuccess : (List<LaunchVO>) -> Unit,
        onFailure : (String) -> Unit,
    )
}