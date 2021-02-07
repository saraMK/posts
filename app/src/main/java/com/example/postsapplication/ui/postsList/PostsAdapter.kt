package com.example.postsapplication.ui.postsList

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.postsapplication.R
import com.example.postsapplication.databinding.PostItemBinding
import com.example.postsapplication.models.PostModel
import java.lang.reflect.Array

class PostsAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: MutableList<PostModel> = ArrayList<PostModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflate: PostItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.post_item, parent, false
        )
        return PostsViewHolder(
            inflate
            ,
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PostsViewHolder -> {
                holder.bind(list.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    fun submitList(list: List<PostModel>) {
        this.list=(list as MutableList<PostModel>)
        notifyDataSetChanged()
    }

    class PostsViewHolder
    constructor(
        val binding: PostItemBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PostModel) = with(binding.root) {
            binding.model=item
            binding.root.setOnClickListener {
                interaction?.onItemSelected(item.id)
            }
        }
    }

    interface Interaction {
        fun onItemSelected(id: Int)
    }

}
