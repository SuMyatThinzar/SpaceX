package com.smtz.betterhr.codetest.spacex.activities

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.smtz.betterhr.codetest.spacex.R
import com.smtz.betterhr.codetest.spacex.data.vos.LaunchVO
import com.smtz.betterhr.codetest.spacex.databinding.ActivityDetailBinding
import com.smtz.betterhr.codetest.spacex.utils.setVisibleOrGone

class DetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private var mLaunchVO: LaunchVO? = null

    companion object {
        private const val EXTRA_LAUNCH_VO_STR = "EXTRA LAUNCH VO STR"

        fun newIntent(context: Context, launchVOStr: String) : Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_LAUNCH_VO_STR, launchVOStr)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val launchVOJsonStr = intent.getStringExtra(EXTRA_LAUNCH_VO_STR) ?: ""
        mLaunchVO = Gson().fromJson(launchVOJsonStr, LaunchVO::class.java) // Convert Json String back to Object

        setUpToolBar()
        setUpListeners()
        bindData()
    }

    private fun setUpToolBar() {
        setSupportActionBar(binding.toolbar)
        // setup back button on toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = mLaunchVO?.missionName ?: getString(R.string.app_name)

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

    private fun bindData() {
        // bind image
        mLaunchVO?.let {
            Glide.with(this)
                .load(mLaunchVO?.links?.patch?.small)
                .into(binding.ivImage)

            binding.tvLaunchName.text = it.missionName
            binding.tvLaunchDate.text = it.launchDateUtc?.substring(0, 10)

            // bind detail text
            checkNullOrEmptyAndBindText(
                content = it.details, textView = binding.tvLaunchDetail, label = null
            )

            // bind failure texts (failure is an array)
            if (it.failures?.isNotEmpty() == true) {

                checkNullOrEmptyAndBindText(
                    content = it.failures[0].time.toString(), textView = binding.tvFailureTimes, label = "Number of Failure : "
                )
                checkNullOrEmptyAndBindText(
                    content = it.failures[0].altitude.toString(), textView = binding.tvFailureAltitude, label = "Altitude of Failure : "
                )
                checkNullOrEmptyAndBindText(
                    content = it.failures[0].reason, textView = binding.tvFailureReason, label = "Reason of Failure : "
                )
            }
        }
    }

    private fun redirectToBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun checkNullOrEmptyAndBindText(content: String?, textView: TextView, label: String?) {

        if (content.isNullOrBlank() || content == "null") {
            textView.setVisibleOrGone(false)   // hide TextView
        } else {
            textView.setVisibleOrGone(true)    // show TextView
            val text = label + content
            textView.text = text
        }
    }

    // setup toolbar's back button listener
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}