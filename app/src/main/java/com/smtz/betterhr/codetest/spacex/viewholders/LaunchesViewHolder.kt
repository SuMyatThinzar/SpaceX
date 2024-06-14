package com.smtz.betterhr.codetest.spacex.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smtz.betterhr.codetest.spacex.Listeners.LaunchesDelegate
import com.smtz.betterhr.codetest.spacex.data.vos.LaunchVO
import com.smtz.betterhr.codetest.spacex.databinding.ViewHolderLaunchesBinding
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class LaunchesViewHolder(
    private val binding: ViewHolderLaunchesBinding,
    private val launchesDelegate: LaunchesDelegate
) : RecyclerView.ViewHolder(binding.root) {

    private var mLaunchVO : LaunchVO? = null

    init {
        itemView.setOnClickListener {
            mLaunchVO?.let { launch ->
                launchesDelegate.onTapLaunches(launch, binding.ivImage)
//                itemView.context.startActivity(DetailActivity.newIntent(itemView.context, launch.links?.article ?: ""))
            }
        }
    }

    fun bindData(launchVO: LaunchVO) {
        mLaunchVO = launchVO

        // Date and Time Formatting
        val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
        val dateTime = ZonedDateTime.parse(launchVO.launchDateUtc, formatter)
        val formattedDate = dateTime.format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss"))

        // bind data to views
        binding.tvLaunchName.text = launchVO.missionName
        binding.tvLaunchDate.text = formattedDate

        if (!launchVO.links?.patch?.small.isNullOrEmpty()) {
            Glide.with(binding.root.context)
                .load(launchVO.links!!.patch.small)
                .into(binding.ivImage)
        }
    }
}