package com.example.rss.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.rss.R
import com.example.rss.presentation.NewsFragment
import com.example.rss.presentation.NewsFragmentDirections

internal  fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(getAdapterPosition(), getItemViewType())
    }
    return this
}

class NewsItemAdapter(private val newsItems: List<NewsItem>): RecyclerView.Adapter<NewsItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)

        val layoutParams: ViewGroup.LayoutParams = view.getLayoutParams()
        layoutParams.height = ((parent.height * 0.3).toInt())
        view.setLayoutParams(layoutParams)

        return ViewHolder(view).listen{ pos, type ->
            val action = NewsFragmentDirections.actionNewsFragmentToDetailsFragment("https://edition.cnn.com/services/rss/")
            Navigation.findNavController(view).navigate(action)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsItem = newsItems[position]

        holder.newsTitle.text = newsItem.title
        holder.newsDescription.text = newsItem.description
        holder.newsLink.text = newsItem.link
    }

    override fun getItemCount(): Int {
        return newsItems.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val newsTitle: TextView = itemView.findViewById(R.id.newsTitle)
        val newsDescription: TextView = itemView.findViewById(R.id.newsDescription)
        val newsLink: TextView = itemView.findViewById(R.id.newsLink)
    }
}