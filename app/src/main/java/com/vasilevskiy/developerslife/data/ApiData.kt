package com.vasilevskiy.developerslife.data

import com.google.gson.annotations.SerializedName

data class ApiData(
    @SerializedName("id")
    val id : Int,
    @SerializedName("description")
    val description : String,
    @SerializedName("gifURL")
    val url : String
)