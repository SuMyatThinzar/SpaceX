package com.smtz.betterhr.codetest.spacex.viewpods

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.smtz.betterhr.codetest.spacex.R
import com.smtz.betterhr.codetest.spacex.data.vos.LaunchpadVO
import com.smtz.betterhr.codetest.spacex.data.vos.PayloadVO
import com.smtz.betterhr.codetest.spacex.data.vos.RocketVO
import com.smtz.betterhr.codetest.spacex.databinding.ViewPodRelatedFieldBinding
import com.smtz.betterhr.codetest.spacex.utils.addNewTextView
import com.smtz.betterhr.codetest.spacex.utils.bindImage
import com.smtz.betterhr.codetest.spacex.utils.isValid

class LaunchRelatedFieldsViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    private lateinit var binding : ViewPodRelatedFieldBinding
    private lateinit var linearLayout: LinearLayout

    private var mPayload: PayloadVO? = null
    private var mLaunchpad: LaunchpadVO? = null
    private var mRocket: RocketVO? = null


    override fun onFinishInflate() {
        binding = ViewPodRelatedFieldBinding.bind(this)
        linearLayout = binding.linearLayout
        super.onFinishInflate()
    }

    // call from Activity
    fun setUpPayloadViewPod(payload: PayloadVO){
        mPayload = payload
        hideProgressBarAndShowLayout()

        binding.ivImage.visibility = View.GONE                 // payload doesn't have image
        binding.progressBarImage.visibility = View.GONE
        setUpTitle(resources.getString(R.string.lbl_payload))
        bindPayloadData()
    }

    // call from Activity
    fun setUpLaunchpadViewPod(launchpad: LaunchpadVO){
        mLaunchpad = launchpad
        hideProgressBarAndShowLayout()

        setUpTitle(resources.getString(R.string.lbl_launchpad))
        bindLaunchpadData()
    }

    // call from Activity
    fun setUpRocketViewPod(rocket: RocketVO){
        mRocket = rocket
        hideProgressBarAndShowLayout()

        setUpTitle(resources.getString(R.string.lbl_rocket))
        bindRocketData()
    }

    // after data fetching and to bind
    fun hideProgressBarAndShowLayout() {
        binding.progressBar.visibility = View.GONE
        binding.linearLayout.visibility = View.VISIBLE
    }

    private fun setUpTitle(text: String) {
        binding.tvTitle.text = text
    }

    private fun checkStringValidityAndAddTextView(content: String?, contentToDisplay: String) {

        if (content.isValid()) {           // content.takeIf { it.isValid() }?.let {}
            addNewTextView(context, contentToDisplay, linearLayout)
        }
    }

    private fun bindPayloadData() {

        // Name - check null or empty and create textview and bind
        checkStringValidityAndAddTextView(
            content = mPayload?.name, contentToDisplay = "Payload name : ${mPayload?.name}")
        // Type
        checkStringValidityAndAddTextView(
            content = mPayload?.type, contentToDisplay = "Payload type : ${mPayload?.type}")
        // Customers
        checkStringValidityAndAddTextView(
            content = mPayload?.getCustomerListAsCommaSeparatedString(), contentToDisplay = "Customers : ${mPayload?.getCustomerListAsCommaSeparatedString()}")
        // Manufacturers
        checkStringValidityAndAddTextView(
            content = mPayload?.getManufacturersAsCommaSeparatedString(), contentToDisplay = "Manufacturers : ${mPayload?.getManufacturersAsCommaSeparatedString()}")
        // massKg
        checkStringValidityAndAddTextView(
            content = mPayload?.massKg.toString(), contentToDisplay = "Payload massKg : ${mPayload?.massKg}")
        // massLbs
        checkStringValidityAndAddTextView(
            content = mPayload?.massLbs.toString(), contentToDisplay = "Payload massLbs : ${mPayload?.massLbs}")

    }

    private fun bindLaunchpadData() {

        mLaunchpad?.images?.large?.get(0).let { image ->        // bind image first
            bindImage(context, image, binding.progressBarImage, binding.ivImage)
        }

        // name
        checkStringValidityAndAddTextView(
            content = mLaunchpad?.name, contentToDisplay = "Name : ${mLaunchpad?.name}")
        // fullName
        checkStringValidityAndAddTextView(
            content = mLaunchpad?.name, contentToDisplay = "FullName : ${mLaunchpad?.fullName}")
        // locality
        checkStringValidityAndAddTextView(
            content = mLaunchpad?.locality, contentToDisplay = "Launchpad locality : ${mLaunchpad?.locality}")
        // latitude
        checkStringValidityAndAddTextView(
            content = mLaunchpad?.latitude, contentToDisplay = "Launchpad latitude : ${mLaunchpad?.latitude}")
        // longitude
        checkStringValidityAndAddTextView(
            content = mLaunchpad?.longitude, contentToDisplay = "Launchpad longitude : ${mLaunchpad?.longitude}")
        // launchAttempts
        checkStringValidityAndAddTextView(
            content = mLaunchpad?.launchAttempts, contentToDisplay = "Launch Attempts : ${mLaunchpad?.launchAttempts}")
        // costPerLaunch
        checkStringValidityAndAddTextView(
            content = "${mLaunchpad?.costPerLaunch}", contentToDisplay = "Cost Per Launch : ${mLaunchpad?.costPerLaunch}")

    }

    private fun bindRocketData() {

        mRocket?.flickrImages?.get(0).let { image ->      // bind image first
            bindImage(context, image, binding.progressBarImage, binding.ivImage)
        }

        // name
        checkStringValidityAndAddTextView(
            content = mRocket?.name, contentToDisplay = "Name : ${mRocket?.name}")
        // country
        checkStringValidityAndAddTextView(
            content = mRocket?.country, contentToDisplay = "Country : ${mRocket?.country}")
        // company
        checkStringValidityAndAddTextView(
            content = mRocket?.company, contentToDisplay = "Name : ${mRocket?.company}")
        // firstFlight
        checkStringValidityAndAddTextView(
            content = mRocket?.firstFlight, contentToDisplay = "First Flight : ${mRocket?.firstFlight}")
        // successRatePct
        checkStringValidityAndAddTextView(
            content = "${mRocket?.successRatePct}", contentToDisplay = "Success Rate : ${mRocket?.successRatePct}")
        // costPerLaunch
        checkStringValidityAndAddTextView(
            content = "${mRocket?.costPerLaunch}", contentToDisplay = "Cost Per Launch : ${mRocket?.costPerLaunch}")
        // description
        checkStringValidityAndAddTextView(
            content = mRocket?.description, contentToDisplay = "Description : ${mRocket?.description}")

    }

}