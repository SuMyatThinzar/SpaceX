package com.smtz.betterhr.codetest.spacex.utils

import android.app.Activity
import android.content.Context
import android.transition.Fade
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.smtz.betterhr.codetest.spacex.activities.LaunchDetailActivity

fun showToastMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun View.setVisibleOrGone(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun hideProgressBar(progressBar: ProgressBar) {
    progressBar.setVisibleOrGone(false)
}

fun checkNullOrEmptyAndBindText(content: String?, textView: TextView, label: String?) {

    if (content.isNullOrBlank() || content == "null") {
        textView.setVisibleOrGone(false)   // hide TextView
    } else {
        textView.setVisibleOrGone(true)    // show TextView
        val text = label + content
        textView.text = text
    }
}