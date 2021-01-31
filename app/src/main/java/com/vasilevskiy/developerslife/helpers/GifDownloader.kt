package com.vasilevskiy.developerslife.helpers

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vasilevskiy.developerslife.R
import java.lang.StringBuilder

class GifDownloader(private val context: Context) {

    fun load(url: String, view: ImageView) {

        Glide.with(context)
            .asGif()
            .load(addChar(url))
            .placeholder(R.drawable.progress_bar)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view)
    }

    private fun addChar(url: String): String {
        var builder = StringBuilder(url)
        return builder.insert(4, "s").toString()
    }

}