package com.smtz.betterhr.codetest.spacex.data.model

import android.annotation.SuppressLint
import com.smtz.betterhr.codetest.spacex.data.vos.LaunchVO
import com.smtz.betterhr.codetest.spacex.network.SpaceXApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class LaunchRepositoryImpl(private var mSpaceXApi: SpaceXApi) : LaunchRepository {

    @SuppressLint("CheckResult")
    override fun getLaunchList(
        onSuccess: (List<LaunchVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mSpaceXApi.getLaunchList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                onSuccess(response)

            }, {
                onFailure(it.localizedMessage ?: "error")
            })
    }
}