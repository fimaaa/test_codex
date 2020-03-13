package com.example.testcodex.ui.adapter.story

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testcodex.R
import com.example.testcodex.databinding.ItemStoriesBinding
import com.example.testcodex.model.response.StoryResponse
import com.example.testcodex.ui.activity.detailstories.DetailStoriesActivity


class StoryAdapter:RecyclerView.Adapter<StoryAdapter.ViewHolder>() {
    private val listTopStory: MutableList<StoryResponse?>? = mutableListOf()

    var titleFavorite = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemStoriesBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_stories,
            parent,
            false
        )
        return ViewHolder(
            binding
        )
    }

    fun resetData(){
        listTopStory?.clear()
        notifyDataSetChanged()
    }

    fun addData(story:StoryResponse?){
        listTopStory?.add(story)
        notifyItemChanged(itemCount)
    }

    fun changeFavorite(title:String?){
        this.titleFavorite = title?:""
    }

    override fun getItemCount(): Int {
        return  listTopStory?.size?:0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listTopStory?.get(position))
        holder.itemView.setOnClickListener {
            val mContext = holder.itemView.context
            println("title = ${listTopStory?.get(position)?.title}")
            println("title Favorite = $titleFavorite")
            val isFavorite = listTopStory?.get(position)?.title == titleFavorite
            println("title isFavorite = $isFavorite")
            (mContext as Activity).startActivityForResult(
                DetailStoriesActivity.startActivity(
                    mContext,
                    listTopStory?.get(position),
                    isFavorite
                ),DetailStoriesActivity.RESULT_DETAIL
            )
        }
    }

    class ViewHolder(private val binding: ItemStoriesBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel =
            StoryViewModel()

        fun bind(item:StoryResponse?){
            viewModel.bind(item)
            binding.viewModel = viewModel
        }
    }


}

