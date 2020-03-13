package com.example.testcodex.ui.activity.topstories

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.testcodex.R
import com.example.testcodex.databinding.ActivityTopstoriesBinding
import com.example.testcodex.ui.activity.detailstories.DetailStoriesActivity


class TopStoriesActivity:AppCompatActivity() {
    private lateinit var binding:ActivityTopstoriesBinding
    private lateinit var viewModel:TopStoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_topstories)
        viewModel =
            ViewModelProviders.of(this).get(TopStoriesViewModel::class.java)
//        ViewModelProvider(this).get(PostListViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.loadTopStory()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println("requestcode = $requestCode")
        if (requestCode == DetailStoriesActivity.RESULT_DETAIL) {
            println("resul = $resultCode")
            if (resultCode == Activity.RESULT_OK) {
                val favorite = data?.getBooleanExtra(DetailStoriesActivity.INTENT_ISFAVORITE,false)?:false
                val dataResponse = DetailStoriesActivity.getResponse(
                    intent.getStringExtra(
                        DetailStoriesActivity.INTENT_RESPONSE_STORY
                    )?:""
                )
                println("favorite = $favorite")
                println("hasil dataString = ${intent.getStringExtra(DetailStoriesActivity.INTENT_RESPONSE_STORY)}")
                println("hasil dataResponse = $dataResponse")
                if(favorite){
                    viewModel.favoriteStory.value = dataResponse?.title?:"Title Not Found"
                }
            }
        }
    }

}