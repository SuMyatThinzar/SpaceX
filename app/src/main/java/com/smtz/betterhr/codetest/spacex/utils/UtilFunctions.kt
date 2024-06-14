package com.smtz.betterhr.codetest.spacex.utils

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

fun showToastMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun View.setVisibleOrGone(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun hideProgressBar(progressBar: ProgressBar) {
    progressBar.setVisibleOrGone(false)
}