package com.dutch.lazylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dutch.lazylist.databinding.VerticalListItemBinding

class InfiniteListAdapter(private val items: MutableList<ListItemData>) :
    RecyclerView.Adapter<InfiniteListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            VerticalListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: VerticalListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListItemData) {
            // Bind the data to the views using the ViewBinding instance
            binding.tv1.text = item.title
            binding.tv2.text = item.subtitle
            binding.tv3.text = item.description
            binding.tv4.text = item.date
            binding.tv5.text = item.author
            binding.tv6.text = item.status
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateList(newItems: List<ListItemData>) {
        val start = items.size
        items.addAll(newItems)
        notifyItemRangeChanged(start, newItems.size)
    }
}