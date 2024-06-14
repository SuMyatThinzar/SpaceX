package com.smtz.betterhr.codetest.spacex.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smtz.betterhr.codetest.spacex.data.model.LaunchRepository
import com.smtz.betterhr.codetest.spacex.data.model.LaunchRepositoryImpl
import com.smtz.betterhr.codetest.spacex.data.vos.LaunchVO

class LaunchListingViewModel(private var mLaunchRepository: LaunchRepository) : ViewModel() {

//    private var mLaunchRepository: LaunchRepository = LaunchRepositoryImpl()

    var mLaunchesLiveData = MutableLiveData<List<LaunchVO>>()
    var mErrorLiveData = MutableLiveData<String>()

    fun getInitialData() {
        mLaunchRepository.getLaunchList(
            onSuccess = { launchList ->
                mLaunchesLiveData.postValue(launchList)
            },
            onFailure = { errorMsg ->
                mErrorLiveData.postValue(errorMsg)
            }
        )
    }
}