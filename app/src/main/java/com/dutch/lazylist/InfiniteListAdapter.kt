package com.dutch.lazylist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dutch.lazylist.databinding.VerticalListItemBinding

class InfiniteListAdapter(private val items: MutableList<ListItemData>) :
    RecyclerView.Adapter<InfiniteListAdapter.ViewHolder>() {

        val TAG = "InfiniteListAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder()")
        val binding =
            VerticalListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: VerticalListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListItemData) {
            // Bind the data to the views using the ViewBinding instance
            Log.i(TAG, "bind() $item")
            binding.tv1.text = item.title
            binding.tv2.text = item.subtitle
            binding.tv3.text = item.description
            binding.tv4.text = item.date
            binding.tv5.text = item.author
            binding.tv6.text = item.status
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder()")
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        Log.i(TAG, "getItemCount: ${items.size}")
        return items.size
    }

    fun updateList(newItems: List<ListItemData>) {
        Log.i(TAG, "updateList: $newItems")
        val start = items.size
        items.addAll(newItems)
        notifyItemRangeChanged(start, newItems.size)
    }
}