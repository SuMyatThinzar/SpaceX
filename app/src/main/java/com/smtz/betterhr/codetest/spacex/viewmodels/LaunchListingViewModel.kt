package com.smtz.betterhr.codetest.spacex.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smtz.betterhr.codetest.spacex.data.model.LaunchModel
import com.smtz.betterhr.codetest.spacex.data.model.LaunchModelImpl
import com.smtz.betterhr.codetest.spacex.data.vos.LaunchVO

class LaunchListingViewModel : ViewModel() {

    private var mLaunchModel: LaunchModel = LaunchModelImpl

    var mLaunchesLiveData = MutableLiveData<List<LaunchVO>>()
    var mErrorLiveData = MutableLiveData<String>()

    fun getInitialData() {
        mLaunchModel.getLaunchList(
            onSuccess = { launchList ->
                mLaunchesLiveData.postValue(launchList)
            },
            onFailure = { errorMsg ->
                mErrorLiveData.postValue(errorMsg)
            }
        )
    }
}