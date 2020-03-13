package com.example.testcodex.ui.activity.detailstories

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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
        const val INTENT_ISFAVORITE = "intent_story_favorite"
        const val RESULT_DETAIL = 1

        fun startActivity(mContext: Context, storyResponse: StoryResponse?,isFavorite:Boolean): Intent {
            val responseString = Gson().toJson(storyResponse)
            val intent = Intent(mContext,DetailStoriesActivity::class.java)
            intent.putExtra(INTENT_RESPONSE_STORY,responseString)
            intent.putExtra(INTENT_ISFAVORITE,isFavorite)
            return  intent
        }

        fun getResponse(dataString:String): StoryResponse? {
            return Gson().fromJson(
                dataString,
                object : TypeToken<StoryResponse?>() {}.type
            )
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detailstories)
        viewModel =
            ViewModelProviders.of(this).get(DetailStoriesViewModel::class.java)
//        ViewModelProvider(this).get(PostListViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.bind(
            getResponse(intent.getStringExtra(INTENT_RESPONSE_STORY)?:""),
            intent.getBooleanExtra(INTENT_ISFAVORITE,false)
        )
        viewModel.isFavorite.observe(this, Observer {
            when(it){
                true->{
                    binding.ivFavoriteStory.setImageDrawable(resources.getDrawable(R.drawable.ic_favorit_on))
                }
                else->{
                    binding.ivFavoriteStory.setImageDrawable(resources.getDrawable(R.drawable.ic_favorit_off))
                }
            }
        })
    }

    override fun finish() {
        println("hasil dataResponse = ${intent.getStringExtra(INTENT_RESPONSE_STORY)}")

        val responseString = intent.getStringExtra(INTENT_RESPONSE_STORY)
        println("hasil responseString = ${intent.getStringExtra(DetailStoriesActivity.INTENT_RESPONSE_STORY)}")

        val intent = Intent()
        intent.putExtra(INTENT_RESPONSE_STORY,responseString)
        intent.putExtra(INTENT_ISFAVORITE, viewModel.isFavorite.value)
        setResult(RESULT_OK, intent)
        super.finish()
    }
}