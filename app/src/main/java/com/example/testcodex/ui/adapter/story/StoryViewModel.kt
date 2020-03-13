package com.example.testcodex.ui.adapter.story

import androidx.lifecycle.MutableLiveData
import com.example.testcodex.model.response.StoryResponse

class StoryViewModel {

    val titleStory = MutableLiveData<String>()
    val scoreStory = MutableLiveData<String>()
    val sumCommentStory = MutableLiveData<String>()

    fun bind(story:StoryResponse?){
        titleStory.value = story?.title
        scoreStory.value = "score = ${story?.score.toString()}"
        sumCommentStory.value = "komentar = ${story?.kids?.size.toString()}"
    }
}