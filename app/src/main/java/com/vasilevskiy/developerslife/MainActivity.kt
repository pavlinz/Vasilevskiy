package com.vasilevskiy.developerslife

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vasilevskiy.developerslife.helpers.GifDownloader
import com.vasilevskiy.developerslife.repository.Repository
import com.vasilevskiy.developerslife.util.invisible
import com.vasilevskiy.developerslife.util.visible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    private lateinit var viewModel: MainViewModel
    private var position = 0
    private var lastLoaded = true

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        val gifDownloader = GifDownloader(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        if (viewModel.checkInternetConnection(applicationContext)!!) {
            viewModel.getPost()
        } else {
            hideNoConnectionViews(true)
        }

        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                if (lastLoaded) {
                    viewModel.rememberResponseData(position, response.body()?.id)
                }

                tv_post_description.text = response.body()?.description

                gifDownloader.load(response.body()?.url!!, image_view_main_gif)
            } else {
                Log.d(TAG, response.errorBody().toString())
            }
        })

        fab_next_post.setOnClickListener {
            if (viewModel.checkInternetConnection(applicationContext)!!) {
                if (!lastLoaded) {
                    changeSettings(true)
                    viewModel.getPostById(position)
                    if ((position + 1) == viewModel.getIdsLength()) {
                        lastLoaded = true
                    }
                } else {
                    changeSettings(true)
                    viewModel.getPost()
                }
            } else {
                hideNoConnectionViews(true)
            }
        }

        fab_previous_post.setOnClickListener {
            if (viewModel.checkInternetConnection(applicationContext)!!) {
                changeSettings(false)
                viewModel.getPostById(position)
            } else {
                hideNoConnectionViews(true)
            }
        }

        btn_connect.setOnClickListener {
            if (viewModel.checkInternetConnection(applicationContext)!!){
                hideNoConnectionViews(false)
                viewModel.getPost()
            } else {
                hideNoConnectionViews(true)
            }
        }
    }

    private fun changeSettings(flag: Boolean) {
        if (flag) {
            position++
            fab_previous_post.isEnabled = true
        } else {
            lastLoaded = false
            position--
            if (position == 0) {
                fab_previous_post.isEnabled = false
            }
        }
    }

    private fun hideNoConnectionViews(flag: Boolean) {
        if (flag) {
            image_view_no_connection.visible()
            tv_no_connection.visible()
            btn_connect.visible()

            fab_next_post.isEnabled = false
            fab_previous_post.isEnabled = false
            image_view_main_gif.invisible()
            tv_post_description.invisible()
        } else {
            image_view_no_connection.invisible()
            tv_no_connection.invisible()
            btn_connect.invisible()

            fab_next_post.isEnabled = true
            image_view_main_gif.visible()
            tv_post_description.visible()
        }
    }
}
