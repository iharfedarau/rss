package com.example.rss.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rss.R
import com.example.rss.domain.model.RssItem
import com.example.rss.presentation.RssFragmentDirections

internal  fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(getAdapterPosition(), getItemViewType())
    }
    return this
}

class RssItemAdapter(): RecyclerView.Adapter<RssItemAdapter.ViewHolder>() {
    private var newsItems: List<RssItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)

        return ViewHolder(view).listen{ pos, type ->
            val action = RssFragmentDirections.actionNewsFragmentToDetailsFragment(newsItems!![pos].link!!)
            Navigation.findNavController(view).navigate(action)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsItem = newsItems!![position]
        Glide.with(holder.ivMediaContent.getContext()).load(newsItem.mediaContentMediaContent!!.url).into(holder.ivMediaContent);

        holder.newsTitle.text = newsItem.title?:""
        holder.newsDescription.text = newsItem.description
        holder.newsLink.text = newsItem.link
    }

    override fun getItemCount(): Int {
        if (newsItems == null) {
            return 0
        }
        return newsItems!!.size
    }

    fun update(newsItems: List<RssItem>) {
        this.newsItems = newsItems
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val ivMediaContent: ImageView = itemView.findViewById(R.id.ivMediaContent)
        val newsTitle: TextView = itemView.findViewById(R.id.newsTitle)
        val newsDescription: TextView = itemView.findViewById(R.id.newsDescription)
        val newsLink: TextView = itemView.findViewById(R.id.newsLink)
    }
}