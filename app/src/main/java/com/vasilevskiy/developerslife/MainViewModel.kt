package com.vasilevskiy.developerslife

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vasilevskiy.developerslife.data.ApiData
import com.vasilevskiy.developerslife.helpers.NetworkConnection
import com.vasilevskiy.developerslife.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

@Suppress("DEPRECATION")
class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<Response<ApiData>> = MutableLiveData()
    var networkConnection: NetworkConnection? = null

    val ids = mutableMapOf<Int, Int>()

    fun getPost() {
        viewModelScope.launch {
            var response = repository.getPost()
            if (response != null) {
                myResponse.value = response
            } else {
                while (response == null) {
                    response = repository.getPost()
                }
                myResponse.value = response
            }
        }
    }

    fun getPostById(position: Int?) {
        viewModelScope.launch {
            val response = repository.getPostById(ids[position])
            myResponse.value = response
        }
    }

    fun checkInternetConnection(context: Context) : Boolean? {
        networkConnection = NetworkConnection(context)
        return networkConnection?.checkConnection()
    }

    fun rememberResponseData(number: Int, id: Int?) {
        ids.put(number, id!!)
    }

    fun getIdsLength() = ids.size
}