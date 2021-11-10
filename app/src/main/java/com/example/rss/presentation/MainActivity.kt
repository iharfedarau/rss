package com.example.rss.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rss.R
import com.example.rss.data.NewsItem
import com.example.rss.data.NewsItemAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.newsRecyclerView)
        recyclerview.setHasFixedSize(true);
        recyclerview.layoutManager = LinearLayoutManager(this)


        val data = ArrayList<NewsItem>()

        for (i in 1..20) {
            data.add(NewsItem("title $i", "description $i", "link $i"))
        }

        recyclerview.adapter = NewsItemAdapter(data)
    }
}