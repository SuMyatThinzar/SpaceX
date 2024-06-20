package com.smtz.betterhr.codetest.spacex.di

import com.smtz.betterhr.codetest.spacex.Listeners.LaunchesDelegate
import com.smtz.betterhr.codetest.spacex.adapters.LaunchesRecyclerViewAdapter
import com.smtz.betterhr.codetest.spacex.data.model.SpaceXRepository
import com.smtz.betterhr.codetest.spacex.data.model.SpaceXRepositoryImpl
import com.smtz.betterhr.codetest.spacex.network.SpaceXApi
import com.smtz.betterhr.codetest.spacex.utils.BASE_URL
import com.smtz.betterhr.codetest.spacex.viewmodels.LaunchDetailViewModel
import com.smtz.betterhr.codetest.spacex.viewmodels.LaunchListingViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

var appModule = module {

    single {
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(SpaceXApi::class.java)
    }

    single<SpaceXRepository> {
        SpaceXRepositoryImpl(get())
    }

    viewModel {
        LaunchListingViewModel(get())
    }

    viewModel {
        LaunchDetailViewModel(get())
    }

    factory {
        (launchesDelegate: LaunchesDelegate) -> LaunchesRecyclerViewAdapter(launchesDelegate)
    }

}