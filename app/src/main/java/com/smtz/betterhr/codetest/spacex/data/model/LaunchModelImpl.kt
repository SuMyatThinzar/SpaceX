package com.smtz.betterhr.codetest.spacex.data.model

import android.annotation.SuppressLint
import com.smtz.betterhr.codetest.spacex.data.vos.LaunchVO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

object LaunchModelImpl : LaunchModel, BaseModel() {

    @SuppressLint("CheckResult")
    override fun getLaunchList(
        onSuccess: (List<LaunchVO>) -> Unit,
        onFailure: (String) -> Unit
    ){
        mSpaceXApi.getLaunchList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ response ->

//                val launchList = mutableListOf<LaunchVO>()
//
//                response.launches?.forEach { launchVO ->
//                    launchList.add(launchVO)
//                }

                onSuccess(response?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "error")
            })
    }
}