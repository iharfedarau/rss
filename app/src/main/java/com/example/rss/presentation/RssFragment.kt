package com.example.rss.presentation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.rss.databinding.FragmentRssBinding
import com.example.rss.model.RssItemAdapter
import com.example.rss.model.RssItemModel


class RssFragment : Fragment() {
    private lateinit var viewModel: RssItemModel
    private  lateinit var binding: FragmentRssBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRssBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.refreshRssButton.setOnClickListener {
            binding.swipeContainer.setRefreshing(true);
            viewModel.refresh{
                binding.swipeContainer.setRefreshing(false);
            }
        }

        binding.swipeContainer.setOnRefreshListener{
            viewModel.refresh{
                binding.swipeContainer.setRefreshing(false);
            }
        }

        viewModel = ViewModelProvider(this).get(RssItemModel::class.java)

        binding.newsRecyclerView.layoutManager = LinearLayoutManager(
            view.context,
            LinearLayoutManager.VERTICAL,
            false
        )
        val adapter = RssItemAdapter()
        binding.newsRecyclerView.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner, Observer {
            adapter.update(it)
            adapter.notifyDataSetChanged()
        })
    }
}