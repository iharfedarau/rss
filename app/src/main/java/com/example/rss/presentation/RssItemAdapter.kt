package com.example.rss.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rss.R
import com.example.rss.domain.model.RssItem

internal  fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(getAdapterPosition())
    }
    return this
}

class RssItemAdapter(val context: Context): RecyclerView.Adapter<RssItemAdapter.ViewHolder>() {
    private var rssItems = listOf<RssItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rss_item, parent, false)

        return ViewHolder(view).listen{ pos ->
            val action = RssFragmentDirections.actionNewsFragmentToDetailsFragment(rssItems[pos].link!!)
            Navigation.findNavController(view).navigate(action)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsItem = rssItems[position]
        if (newsItem.mediaContentMediaContent != null && newsItem.mediaContentMediaContent.url != null) {
            Glide.with(context)
                .load(newsItem.mediaContentMediaContent.url).apply(RequestOptions().override(100, 100))
                .into(holder.ivMediaContent);
        }
        holder.newsTitle.text = newsItem.title?:""
    }

    override fun getItemCount(): Int {
        return rssItems.size
    }

    fun update(items: List<RssItem>) {
        rssItems = items
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val ivMediaContent: ImageView = itemView.findViewById(R.id.ivMediaContent)
        val newsTitle: TextView = itemView.findViewById(R.id.newsTitle)
    }
}