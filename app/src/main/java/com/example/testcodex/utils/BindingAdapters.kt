package com.example.testcodex.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?:View.VISIBLE})
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value?:""})
    }
}

@BindingAdapter("mutableMax")
fun setMax(view: ProgressBar, max:MutableLiveData<Int>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && max != null) {
        max.observe(parentActivity, Observer {
            view.max = it
        })
    }
}

@BindingAdapter("mutableProgress")
fun setProgress(view: ProgressBar, progress:MutableLiveData<Int>?) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && progress != null) {
        progress.observe(parentActivity, Observer {
            view.progress = it
        })
    }
}

@BindingAdapter("mutableGlide")
fun setMutableGlide(view: ImageView, link:MutableLiveData<String>?){
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && link != null) {
        link.observe(parentActivity, Observer { value ->
            Glide.with(parentActivity)
                .applyDefaultRequestOptions(Constans.getRequestOption())
                .load(value)
                .into(view)
        })
    }
}