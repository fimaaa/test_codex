package com.example.testcodex.utils

import android.annotation.SuppressLint
import com.bumptech.glide.request.RequestOptions
import com.example.testcodex.R

class Constans {
    companion object {
        const val BASE_URL: String = "https://hacker-news.firebaseio.com"

        @SuppressLint("CheckResult")
        fun getRequestOption(): RequestOptions {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.color.light_transparent)
            requestOptions.error(R.color.black)

            return requestOptions
        }
    }
}