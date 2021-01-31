package com.vasilevskiy.developerslife.api

import com.vasilevskiy.developerslife.data.ApiData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface СertainPreviousPostApi {
    @GET("/{id}?json=true")
    suspend fun getGifInfo(@Path("id") id : Int?): Response<ApiData>
}