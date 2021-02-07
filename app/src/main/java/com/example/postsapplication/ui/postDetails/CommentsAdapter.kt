package com.example.postsapplication.ui.postDetails

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.postsapplication.R
import com.example.postsapplication.databinding.CommentItemBinding
import com.example.postsapplication.models.CommentModel
import javax.inject.Inject

class CommentsAdapter @Inject constructor() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var list: MutableList<CommentModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate:CommentItemBinding= DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.comment_item, parent, false
        )
        return MyViewHolder(
            inflate
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MyViewHolder -> {
                holder.bind(list.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun submitList(list: List<CommentModel>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class MyViewHolder
    constructor(
        val binding: CommentItemBinding
     ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CommentModel) = with(itemView) {
            binding.model=item

        }
    }


}
