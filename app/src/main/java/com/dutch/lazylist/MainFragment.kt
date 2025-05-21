package com.dutch.lazylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.dutch.lazylist.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnPaged = view.findViewById<Button>(R.id.btn_paged)
        val btnInfinite = view.findViewById<Button>(R.id.btn_infinite)

        btnPaged.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_pagedListFragment)
        }

        btnInfinite.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_infiniteListFragment)
        }
    }
}