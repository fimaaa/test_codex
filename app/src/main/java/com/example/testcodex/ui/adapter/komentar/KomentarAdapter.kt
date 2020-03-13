package com.example.testcodex.ui.adapter.komentar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testcodex.R
import com.example.testcodex.databinding.ItemKomentarBinding
import com.example.testcodex.model.response.CommentResponse

class KomentarAdapter: RecyclerView.Adapter<KomentarAdapter.ViewHolder>() {
    private val listComment: MutableList<CommentResponse?> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemKomentarBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_komentar,
            parent,
            false
        )
        return ViewHolder(
            binding
        )
    }

    fun addData(comment: CommentResponse){
        listComment.add(comment)
        notifyItemChanged(itemCount)
    }

    override fun getItemCount(): Int {
        return  listComment.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listComment[position])
    }

    class ViewHolder(private val binding: ItemKomentarBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel =
            KomentarViewModel()

        fun bind(item:CommentResponse?){
            viewModel.bind(item)
            binding.viewModel = viewModel
        }
    }
}