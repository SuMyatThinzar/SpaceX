package com.smtz.betterhr.codetest.spacex.data.model

import com.smtz.betterhr.codetest.spacex.data.vos.LaunchVO
import com.smtz.betterhr.codetest.spacex.data.vos.LaunchpadVO
import com.smtz.betterhr.codetest.spacex.data.vos.PayloadVO
import com.smtz.betterhr.codetest.spacex.data.vos.RocketVO
import com.smtz.betterhr.codetest.spacex.network.SpaceXApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SpaceXRepositoryImpl(private var mSpaceXApi: SpaceXApi) : SpaceXRepository {

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

    override fun getPayload(
        id: String,
        onSuccess: (PayloadVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mSpaceXApi.getRelatedPayload(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                onSuccess(response)
            }, {
                onFailure(it.localizedMessage ?: "error")
            })
    }

    override fun getLaunchpad(
        id: String,
        onSuccess: (LaunchpadVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mSpaceXApi.getRelatedLaunchpad(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                onSuccess(response)
            }, {
                onFailure(it.localizedMessage ?: "error")
            })
    }

    override fun getRocket(id: String, onSuccess: (RocketVO) -> Unit, onFailure: (String) -> Unit) {
        mSpaceXApi.getRelatedRocket(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                onSuccess(response)
            }, {
                onFailure(it.localizedMessage ?: "error")
            })
    }

}