package com.smtz.betterhr.codetest.spacex.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smtz.betterhr.codetest.spacex.data.model.SpaceXRepository
import com.smtz.betterhr.codetest.spacex.data.vos.LaunchpadVO
import com.smtz.betterhr.codetest.spacex.data.vos.PayloadVO
import com.smtz.betterhr.codetest.spacex.data.vos.RocketVO
import com.smtz.betterhr.codetest.spacex.utils.isValid

class LaunchDetailViewModel(private val mSpaceXRepository: SpaceXRepository) : ViewModel() {

    var mPayloadLiveData = MutableLiveData<PayloadVO>()
    var mLaunchpadLiveData = MutableLiveData<LaunchpadVO>()
    var mRocketLiveData = MutableLiveData<RocketVO>()
    var mErrorLiveData = MutableLiveData<String>()

    fun getInitialData(payloadId: String?, launchpadId: String?, rocketId: String?) {

        if (payloadId.isValid()) {
            mSpaceXRepository.getPayload(
                id = payloadId!!,
                onSuccess = { payload ->
                    mPayloadLiveData.postValue(payload)
                },
                onFailure = { errorMsg ->
                    mErrorLiveData.postValue(errorMsg)
                }
            )
        }

        if (launchpadId.isValid()) {
            mSpaceXRepository.getLaunchpad(
                id = launchpadId!!,
                onSuccess = { launchpad ->
                    mLaunchpadLiveData.postValue(launchpad)
                },
                onFailure = { errorMsg ->
                    mErrorLiveData.postValue(errorMsg)
                }
            )
        }

        if (rocketId.isValid()) {
                mSpaceXRepository.getRocket(
                    id = rocketId!!,
                    onSuccess = { rocket ->
                        mRocketLiveData.postValue(rocket)
                    },
                    onFailure = { errorMsg ->
                        mErrorLiveData.postValue(errorMsg)
                    }
                )
            }
    }
}