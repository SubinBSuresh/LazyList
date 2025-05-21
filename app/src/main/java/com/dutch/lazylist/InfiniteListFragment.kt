package com.dutch.lazylist

import android.graphics.pdf.models.ListItem
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.postDelayed
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dutch.lazylist.databinding.FragmentInfiniteListBinding


class InfiniteListFragment : Fragment() {
    val TAG = "InfiniteListFragment"
    private var _binding: FragmentInfiniteListBinding? = null
    private val binding get() =  _binding!!
    private lateinit var listAdapter: InfiniteListAdapter
    private lateinit var dataList: List<ListItemData>
    private var page = 0
    private lateinit var recyclerView: RecyclerView
    private var isLoading = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView()")
        _binding = FragmentInfiniteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG, "onViewCreated()")
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentInfiniteListBinding.bind(view)
        recyclerView = binding.rvListInfinite
        Log.i(TAG, "here")
        listAdapter = InfiniteListAdapter(mutableListOf())
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        loadMoreData()
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(rv, dx, dy)
                val layoutManager = rv.layoutManager as LinearLayoutManager
                val lastVisible = layoutManager.findLastVisibleItemPosition()
                val total = listAdapter.itemCount

                if (!isLoading && lastVisible >= total - 3) {
                    loadMoreData()
                }
            }
        })

    }


    private fun loadMoreData() {
        Log.i(TAG, "loadMoreData()")
        isLoading = true

        // Simulate delay
        Handler(Looper.getMainLooper()).postDelayed({
            val newItems = List(20) {
                ListItemData(
                    title = "Title ${(page * 20) + it + 1}",
                    subtitle = "Subtitle ${(page * 20) + it + 1}",
                    description = "Description for item ${(page * 20) + it + 1}",
                    date = "2025-05-${(it + 1)}",
                    author = "Author ${(page * 20) + it + 1}",
                    status = if (it % 2 == 0) "Active" else "Inactive"
                )
            }
            listAdapter.updateList(newItems)
            page++
            isLoading = false
        }, 1000)
    }


    override fun onDestroyView() {
        Log.i(TAG, "onDestroyView()")
        super.onDestroyView()
        _binding = null
    }


}