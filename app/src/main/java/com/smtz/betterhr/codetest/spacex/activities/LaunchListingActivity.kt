package com.smtz.betterhr.codetest.spacex.activities

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.smtz.betterhr.codetest.spacex.Listeners.LaunchesDelegate
import com.smtz.betterhr.codetest.spacex.adapters.LaunchesRecyclerViewAdapter
import com.smtz.betterhr.codetest.spacex.data.vos.LaunchVO
import com.smtz.betterhr.codetest.spacex.databinding.ActivityLaunchListingBinding
import com.smtz.betterhr.codetest.spacex.utils.*
import com.smtz.betterhr.codetest.spacex.viewmodels.LaunchListingViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class LaunchListingActivity : AppCompatActivity(), LaunchesDelegate {

    private val binding by lazy {
        ActivityLaunchListingBinding.inflate(layoutInflater)
    }

    private val mLaunchListingViewModel by viewModel<LaunchListingViewModel>()

    private val mLaunchesAdapter by inject<LaunchesRecyclerViewAdapter> { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpViewModel()

        setUpAdapters()

        observeLiveData()
    }

    private fun setUpViewModel() {
//        mLaunchListingViewModel = ViewModelProvider(this)[LaunchListingViewModel::class.java]
        mLaunchListingViewModel.getInitialData()
    }

    private fun setUpAdapters() {

        binding.rvSpaceXLaunches.apply {
            layoutManager = LinearLayoutManager(this@LaunchListingActivity)
            adapter = mLaunchesAdapter
        }
    }

    private fun observeLiveData() {
        mLaunchListingViewModel.mLaunchesLiveData.observe(this) { launchList ->
            hideProgressBar(binding.progressBar)
            mLaunchesAdapter.setNewData(launchList)
        }

        mLaunchListingViewModel.mErrorLiveData.observe(this) { errorMsg ->
            hideProgressBar(binding.progressBar)
            showToastMessage(applicationContext, errorMsg)
        }
    }

    override fun onTapLaunches(launchVO: LaunchVO, imageView: ImageView) {
        // Convert the object to a JSON string
        val jsonString = Gson().toJson(launchVO)
        startActivity(LaunchDetailActivity.newIntent(this, jsonString))
    }

}
