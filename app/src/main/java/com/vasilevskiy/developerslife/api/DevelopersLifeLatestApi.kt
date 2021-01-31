package com.vasilevskiy.developerslife.api

import com.vasilevskiy.developerslife.data.ApiData
import retrofit2.Response
import retrofit2.http.GET

interface DevelopersLifeLatestApi {
    @GET("/random?json=true")
    suspend fun getGifInfo(): Response<ApiData>
}