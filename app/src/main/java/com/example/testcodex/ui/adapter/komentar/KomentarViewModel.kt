package com.example.testcodex.ui.adapter.komentar

import androidx.lifecycle.MutableLiveData
import com.example.testcodex.base.BaseViewModel
import com.example.testcodex.model.response.CommentResponse

class KomentarViewModel:BaseViewModel() {

    val nameCommentator = MutableLiveData<String>()
    val detailComment = MutableLiveData<String>()

    fun bind(comment:CommentResponse?){
        nameCommentator.value = "${comment?.by} :"
        detailComment.value = comment?.text
    }
}