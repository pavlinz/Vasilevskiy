package com.vasilevskiy.developerslife.repository

import com.vasilevskiy.developerslife.api.RetrofitInstance
import com.vasilevskiy.developerslife.data.ApiData
import retrofit2.Response

class Repository {

    suspend fun getPost() : Response<ApiData> {
        return RetrofitInstance.latestPostsService.getGifInfo()
    }

    suspend fun getPostById(id: Int?) : Response<ApiData> {
        return RetrofitInstance.certainPreviousPostService.getGifInfo(id)
    }

}