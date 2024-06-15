package com.smtz.betterhr.codetest.spacex.activities

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.smtz.betterhr.codetest.spacex.R
import com.smtz.betterhr.codetest.spacex.data.vos.LaunchVO
import com.smtz.betterhr.codetest.spacex.data.vos.PayloadVO
import com.smtz.betterhr.codetest.spacex.databinding.ActivityDetailBinding
import com.smtz.betterhr.codetest.spacex.utils.checkNullOrEmptyAndBindText
import com.smtz.betterhr.codetest.spacex.utils.*
import com.smtz.betterhr.codetest.spacex.viewmodels.LaunchDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LaunchDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    private val mLaunchDetailViewModel by viewModel<LaunchDetailViewModel>()

    private var mLaunchVO: LaunchVO? = null

    companion object {
        private const val EXTRA_LAUNCH_VO_STR = "EXTRA LAUNCH VO STR"

        fun newIntent(context: Context, launchVOStr: String) : Intent {
            val intent = Intent(context, LaunchDetailActivity::class.java)
            intent.putExtra(EXTRA_LAUNCH_VO_STR, launchVOStr)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val launchVOJsonStr = intent.getStringExtra(EXTRA_LAUNCH_VO_STR) ?: ""
        mLaunchVO = Gson().fromJson(launchVOJsonStr, LaunchVO::class.java) // Convert Json String back to Object

        setUpViewModel()

        setUpToolBar()
        setUpListeners()
        bindLaunchData()

        observeLiveData()
    }

    private fun setUpViewModel() {
        mLaunchVO?.let { launch ->
            launch.payloads?.get(0)?.let { payloadId ->
                mLaunchDetailViewModel.getInitialData(payloadId)
            } ?: binding.llPayload.setVisibleOrGone(false)          // hide payload view group
        }
    }

    private fun observeLiveData() {
        mLaunchDetailViewModel.mPayloadLiveData.observe(this) { payload ->
            hideProgressBar(binding.progressBar)
            binding.llPayload.visibility = View.VISIBLE
            bindPayloadData(payload)
        }

        mLaunchDetailViewModel.mErrorLiveData.observe(this) { errorMsg ->
            hideProgressBar(binding.progressBar)
            showToastMessage(applicationContext, errorMsg)
        }
    }

    private fun setUpToolBar() {
        setSupportActionBar(binding.toolbar)
        // setup back button on toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = mLaunchVO?.name ?: getString(R.string.app_name)

        // back button color
        binding.toolbar.navigationIcon?.setColorFilter(
            ContextCompat.getColor(this, R.color.color_accent),
            PorterDuff.Mode.SRC_ATOP
        )
    }

    private fun setUpListeners() {

        // Implicit
        mLaunchVO?.links?.article?.let { articleLink ->
            binding.btnReadArticle.setOnClickListener {
                redirectToBrowser(articleLink)
            }
        } ?: binding.btnReadArticle.setVisibleOrGone(false)

        mLaunchVO?.links?.wikipedia?.let { wikipediaLink ->
            binding.ibWiki.setOnClickListener {
                redirectToBrowser(wikipediaLink)
            }
        } ?: binding.ibWiki.setVisibleOrGone(false)

        // Explicit
        mLaunchVO?.links?.webcast?.let { youtubeLink ->
            binding.ibYoutube.setOnClickListener {
                startActivity(ArticleActivity.newIntent(this, youtubeLink))
            }
        } ?: binding.ibYoutube.setVisibleOrGone(false)
    }

    private fun bindPayloadData(payload: PayloadVO) {
        // bind payload text
        checkNullOrEmptyAndBindText(content = payload.name, textView = binding.tvPayloadName, label = "Name : ")
        checkNullOrEmptyAndBindText(content = payload.type, textView = binding.tvPayloadType, label = "Type : ")
        checkNullOrEmptyAndBindText(content = payload.getCustomerListAsCommaSeparatedString(), textView = binding.tvPayloadCustomers, label = "Customers : ")
        checkNullOrEmptyAndBindText(content = payload.getManufacturersAsCommaSeparatedString(), textView = binding.tvPayloadManufacturers, label = "Manufacturers : ")
        checkNullOrEmptyAndBindText(content = payload.massKg.toString(), textView = binding.tvPayloadMassKg, label = "MassKg : ")
        checkNullOrEmptyAndBindText(content = payload.massLbs.toString(), textView = binding.tvPayloadMassLbs, label = "MassLbs : ")

    }

    private fun bindLaunchData() {
        // bind image
        mLaunchVO?.let {
            Glide.with(this)
                .load(mLaunchVO?.links?.patch?.small)
                .into(binding.ivImage)

            binding.tvLaunchName.text = it.name
            binding.tvLaunchDate.text = it.launchDateUtc?.substring(0, 10)

            // bind detail text
            checkNullOrEmptyAndBindText(content = it.details, textView = binding.tvLaunchDetail, label = null)

            // bind failure texts (failure is an array)
            if (it.failures?.isNotEmpty() == true) {

                checkNullOrEmptyAndBindText(content = it.failures[0].time.toString(), textView = binding.tvFailureTimes, label = "Number of Failure : ")
                checkNullOrEmptyAndBindText(content = it.failures[0].altitude.toString(), textView = binding.tvFailureAltitude, label = "Altitude of Failure : ")
                checkNullOrEmptyAndBindText(content = it.failures[0].reason, textView = binding.tvFailureReason, label = "Reason of Failure : ")
            }
        }
    }

    private fun redirectToBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }


    // setup toolbar's back button listener
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}