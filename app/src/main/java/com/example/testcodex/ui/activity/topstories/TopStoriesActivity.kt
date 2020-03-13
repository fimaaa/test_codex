package com.example.testcodex.ui.activity.topstories

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.testcodex.R
import com.example.testcodex.databinding.ActivityTopstoriesBinding
import com.example.testcodex.ui.activity.detailstories.DetailStoriesActivity


class TopStoriesActivity:AppCompatActivity() {
    private lateinit var binding:ActivityTopstoriesBinding
    private lateinit var viewModel:TopStoriesViewModel

    companion object{
        const val INTENT_RESPONSE_STORY = "intent_top_response_story"
        const val INTENT_ISFAVORITE = "intent_top_favorite_story"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_topstories)
        viewModel =
            ViewModelProviders.of(this).get(TopStoriesViewModel::class.java)
//        ViewModelProvider(this).get(PostListViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.favoriteStory.observe(this, Observer {
            viewModel.listTopStoryAdapter.changeFavorite(it)
        })
        viewModel.loadTopStory()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == DetailStoriesActivity.RESULT_DETAIL) {
            if (resultCode == Activity.RESULT_OK) {
                val favorite = data?.getBooleanExtra(INTENT_ISFAVORITE,false)?:false
                val responseString = data?.getStringExtra(INTENT_RESPONSE_STORY)?:""
                val dataResponse = DetailStoriesActivity.getResponse(responseString)
                if(favorite){
                    viewModel.favoriteStory.value = dataResponse?.title
                }else if(dataResponse?.title == viewModel.favoriteStory.value){
                    viewModel.favoriteStory.value = ""
                }
            }
        }
    }

}