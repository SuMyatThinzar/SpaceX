package com.smtz.betterhr.codetest.spacex.activities

import android.os.Bundle
import android.transition.Fade
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.smtz.betterhr.codetest.spacex.Listeners.LaunchesDelegate
import com.smtz.betterhr.codetest.spacex.adapters.LaunchesRecyclerViewAdapter
import com.smtz.betterhr.codetest.spacex.data.vos.LaunchVO
import com.smtz.betterhr.codetest.spacex.databinding.ActivityLaunchListingBinding
import com.smtz.betterhr.codetest.spacex.utils.hideProgressBar
import com.smtz.betterhr.codetest.spacex.utils.showToastMessage
import com.smtz.betterhr.codetest.spacex.viewmodels.LaunchListingViewModel

class LaunchListingActivity : AppCompatActivity(), LaunchesDelegate {

    private val binding by lazy {
        ActivityLaunchListingBinding.inflate(layoutInflater)
    }
    private val mLaunchesAdapter by lazy {
        LaunchesRecyclerViewAdapter(this)
    }
    
    private lateinit var mLaunchListingViewModel : LaunchListingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpViewModel()

        setUpAdapters()

        observeLiveData()
    }

    private fun setUpViewModel() {
        mLaunchListingViewModel = ViewModelProvider(this)[LaunchListingViewModel::class.java]
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
            showToastMessage(this, errorMsg)
        }
    }

    override fun onTapLaunches(launchVO: LaunchVO, imageView: ImageView) {
        // Convert the object to a JSON string
        val jsonString = Gson().toJson(launchVO)

        // Transition with image
        val fade = Fade()
        fade.excludeTarget(android.R.id.statusBarBackground, true)
        fade.excludeTarget(android.R.id.navigationBarBackground, true)

        window.enterTransition = fade
        window.exitTransition = fade

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this, imageView, ViewCompat.getTransitionName(imageView)!!
        )

        startActivity(DetailActivity.newIntent(this, jsonString), options.toBundle())
    }

}
