package com.dutch.lazylist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dutch.lazylist.databinding.FragmentPagedListBinding
import kotlinx.coroutines.flow.collectLatest

class PagedListFragment : Fragment() {
    val TAG = "PagedListFragment"

    private lateinit var binding: FragmentPagedListBinding
    private val viewModel: PagedlistViewModel by viewModels()
    private lateinit var adapter: PagedListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView()")
        // Inflate the layout for this fragment
        binding = FragmentPagedListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG, "onViewCreated()")
        super.onViewCreated(view, savedInstanceState)
        adapter = PagedListAdapter()
        binding.rvListPaged.layoutManager = LinearLayoutManager(requireContext())
        binding.rvListPaged.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.pager.collectLatest {
                adapter.submitData(it)
            }
        }
    }


}