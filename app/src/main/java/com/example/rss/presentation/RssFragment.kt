package com.example.rss.presentation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rss.R
import com.example.rss.databinding.FragmentRssBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RssFragment @Inject constructor(
): Fragment() {
    private val viewModel: RssItemViewModel by viewModels()
    private  lateinit var binding: FragmentRssBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRssBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setTitle("https://www.yahoo.com/news/rss");
        binding.toolbar.inflateMenu(R.menu.rss_menu)
        binding.toolbar.setOnMenuItemClickListener { item: MenuItem? ->
            when (item!!.itemId) {
                R.id.refreshItem -> {
                    binding.swipeContainer.setRefreshing(true)
                    viewModel.refresh{ binding.swipeContainer.setRefreshing(false)}
                }
                else -> throw IllegalStateException("Unknown menu item")
            }
            true
        }

        binding.swipeContainer.setOnRefreshListener{
            viewModel.refresh{
                binding.swipeContainer.setRefreshing(false);
            }
        }

        binding.newsRecyclerView.layoutManager = LinearLayoutManager(
            view.context,
            LinearLayoutManager.VERTICAL,
            false
        )
        val adapter = RssItemAdapter(requireContext())
        binding.newsRecyclerView.adapter = adapter
        binding.newsRecyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        viewModel.data.observe(viewLifecycleOwner, Observer {
            adapter.update(it)
            adapter.notifyDataSetChanged()
        })
    }
}