package com.smtz.betterhr.codetest.spacex.Listeners

import android.widget.ImageView
import com.smtz.betterhr.codetest.spacex.data.vos.LaunchVO

interface LaunchesDelegate {
    fun onTapLaunches(launchVO: LaunchVO, imageView: ImageView)
}