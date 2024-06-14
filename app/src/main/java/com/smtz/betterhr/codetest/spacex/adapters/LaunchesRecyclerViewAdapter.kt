package com.smtz.betterhr.codetest.spacex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smtz.betterhr.codetest.spacex.Listeners.LaunchesDelegate
import com.smtz.betterhr.codetest.spacex.data.vos.LaunchVO
import com.smtz.betterhr.codetest.spacex.databinding.ViewHolderLaunchesBinding
import com.smtz.betterhr.codetest.spacex.viewholders.LaunchesViewHolder

class LaunchesRecyclerViewAdapter(private var launchesDelegate: LaunchesDelegate) : RecyclerView.Adapter<LaunchesViewHolder>() {

    private var mLaunchList = listOf<LaunchVO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewHolderLaunchesBinding.inflate(inflater)
        return LaunchesViewHolder(binding, launchesDelegate)
    }

    override fun getItemCount(): Int = mLaunchList.size

    override fun onBindViewHolder(holder: LaunchesViewHolder, position: Int) {
        if (mLaunchList.isNotEmpty()) {
            holder.bindData(mLaunchList[position])
        }
    }

    fun setNewData(launchList: List<LaunchVO>) {
        mLaunchList = launchList
        notifyDataSetChanged()
    }
}