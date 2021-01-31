package com.vasilevskiy.developerslife.api

import com.vasilevskiy.developerslife.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val latestPostsService: DevelopersLifeLatestApi = RetrofitInstance.retrofit
        .create(DevelopersLifeLatestApi::class.java)

    val certainPreviousPostService: СertainPreviousPostApi = RetrofitInstance.retrofit
        .create(СertainPreviousPostApi::class.java)
}