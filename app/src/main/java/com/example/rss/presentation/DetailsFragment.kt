package com.example.rss.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.rss.R
import com.example.rss.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {
    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.detailsWebView.webViewClient = WebViewClient()
        binding.detailsWebView.loadUrl(args.newsId)
    }
}