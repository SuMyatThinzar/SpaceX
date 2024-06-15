package com.smtz.betterhr.codetest.spacex.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smtz.betterhr.codetest.spacex.data.model.SpaceXRepository
import com.smtz.betterhr.codetest.spacex.data.vos.PayloadVO

class LaunchDetailViewModel(private val mSpaceXRepository: SpaceXRepository) : ViewModel() {

    var mPayloadLiveData = MutableLiveData<PayloadVO>()
    var mErrorLiveData = MutableLiveData<String>()

    fun getInitialData(payloadId: String) {

        mSpaceXRepository.getPayload(
            id = payloadId,
            onSuccess = { payload ->
                mPayloadLiveData.postValue(payload)
            },
            onFailure = { errorMsg ->
                mErrorLiveData.postValue(errorMsg)
            }
        )
    }
}