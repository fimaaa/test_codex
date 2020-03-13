package com.example.testcodex.ui.activity.topstories

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.testcodex.R
import com.example.testcodex.databinding.ActivityTopstoriesBinding

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
}