package com.example.testcodex.ui.activity.detailstories

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.testcodex.R
import com.example.testcodex.databinding.ActivityDetailstoriesBinding
import com.example.testcodex.model.response.StoryResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailStoriesActivity: AppCompatActivity() {
    private lateinit var binding:ActivityDetailstoriesBinding
    private lateinit var viewModel:DetailStoriesViewModel

    companion object{
        const val INTENT_RESPONSE_STORY = "intent_story_response"

        fun startActivity(mContext: Context, storyResponse: StoryResponse?): Intent {
            val responseString = Gson().toJson(storyResponse)
            val intent = Intent(mContext,DetailStoriesActivity::class.java)
            intent.putExtra(INTENT_RESPONSE_STORY,responseString)
            return  intent
        }
    }

    fun getResponse(): StoryResponse? {
        return Gson().fromJson(
            intent.getStringExtra(INTENT_RESPONSE_STORY),
            object : TypeToken<StoryResponse?>() {}.type
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detailstories)
        viewModel =
            ViewModelProviders.of(this).get(DetailStoriesViewModel::class.java)
//        ViewModelProvider(this).get(PostListViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.bind(getResponse())
    }
}