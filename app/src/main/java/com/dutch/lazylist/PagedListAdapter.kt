package com.dutch.lazylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dutch.lazylist.databinding.VerticalListItemBinding

class PagedListAdapter :
    PagingDataAdapter<ListItemData, PagedListAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListItemData>() {
            override fun areItemsTheSame(
                oldItem: ListItemData, newItem: ListItemData
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: ListItemData, newItem: ListItemData
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class ViewHolder(private val binding: VerticalListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListItemData) {
            binding.tv1.text = item.title
            binding.tv2.text = item.subtitle
            binding.tv3.text = item.description
            binding.tv4.text = item.date
            binding.tv5.text = item.author
            binding.tv6.text = item.status
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val binding =
            VerticalListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


}