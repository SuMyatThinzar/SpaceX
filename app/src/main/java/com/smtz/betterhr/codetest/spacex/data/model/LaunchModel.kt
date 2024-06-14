package com.smtz.betterhr.codetest.spacex.data.model

import com.smtz.betterhr.codetest.spacex.data.vos.LaunchVO

interface LaunchModel {

    fun getLaunchList(
        onSuccess : (List<LaunchVO>) -> Unit,
        onFailure : (String) -> Unit,
    )
}