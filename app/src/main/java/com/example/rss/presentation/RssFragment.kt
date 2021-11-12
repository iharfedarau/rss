package com.example.rss.presentation

import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rss.R
import com.example.rss.databinding.FragmentDetailsBinding
import com.example.rss.databinding.FragmentRssBinding
import com.example.rss.model.RssItemAdapter
import com.example.rss.model.RssItemModel

class RssFragment : Fragment() {
    private lateinit var viewModel: RssItemModel
    private  lateinit var binding: FragmentRssBinding

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRssBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.refreshRssButton.setOnClickListener {
            viewModel.refresh()
        }

        viewModel = ViewModelProvider(this).get(RssItemModel::class.java)

        binding.newsRecyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        val adapter = RssItemAdapter()
        binding.newsRecyclerView.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner, Observer {
            adapter.update(it)
            adapter.notifyDataSetChanged()
        })
    }
}