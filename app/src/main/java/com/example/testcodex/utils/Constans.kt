package com.example.testcodex.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.widget.TextView
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

        fun setTextViewHTML(text: TextView, html: String) {
            val sequence = Html.fromHtml(html)
            val strBuilder = SpannableStringBuilder(sequence)
            text.text = strBuilder
            text.movementMethod = LinkMovementMethod.getInstance()
        }
    }
}