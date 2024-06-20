package com.smtz.betterhr.codetest.spacex.utils

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.transition.Fade
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.smtz.betterhr.codetest.spacex.R
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

// Kotlin extension function
fun String?.isValid(): Boolean {
    return !this.isNullOrBlank() && this != "null"    // (same) return !(content.isNullOrBlank() || content == "null")
}

fun addNewTextView(context: Context, text: String, rootView: ViewGroup) {
    val textView = TextView(context)
    textView.text = text
    textView.setTextColor(ContextCompat.getColor(context, R.color.color_accent))
    textView.gravity = Gravity.CENTER

    val layoutParams = ViewGroup.MarginLayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    layoutParams.bottomMargin = context.resources.getDimensionPixelSize(R.dimen.margin_medium)
    textView.layoutParams = layoutParams

    rootView.addView(textView)
}

fun bindImage(context: Context, imageUrl: String?, progressBar: ProgressBar, imageView: ImageView) {

    Glide.with(context)
        .load(imageUrl)
        .apply(RequestOptions.placeholderOf(R.drawable.ic_placeholder))
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                progressBar.visibility = View.GONE
                imageView.setImageResource(R.drawable.ic_placeholder)
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                progressBar.visibility = View.GONE
                return false
            }
        })
        .into(imageView)
}