package com.example.rss.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rss.R
import com.example.rss.data.NewsItemAdapter
import com.example.rss.data.RssItemModel

class RssFragment : Fragment() {
    private lateinit var viewModel: RssItemModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(RssItemModel::class.java)

        val listV = view.findViewById<RecyclerView>(R.id.newsRecyclerView)
        listV.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL ,false)

        val adapter = NewsItemAdapter()
        listV.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner, Observer {
            adapter.update(it)
            adapter.notifyDataSetChanged()
        })
    }
}