package com.smtz.betterhr.codetest.spacex.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smtz.betterhr.codetest.spacex.data.model.SpaceXRepository
import com.smtz.betterhr.codetest.spacex.data.vos.LaunchVO

class LaunchListingViewModel(private var mSpaceXRepository: SpaceXRepository) : ViewModel() {

    var mLaunchesLiveData = MutableLiveData<List<LaunchVO>>()
    var mErrorLiveData = MutableLiveData<String>()

    fun getInitialData() {
        mSpaceXRepository.getLaunchList(
            onSuccess = { launchList ->
                mLaunchesLiveData.postValue(launchList)
            },
            onFailure = { errorMsg ->
                mErrorLiveData.postValue(errorMsg)
            }
        )
    }
}