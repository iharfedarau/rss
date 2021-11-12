package com.example.rss.domain.use_case

import com.example.rss.domain.model.RssItem
import com.example.rss.domain.repository.RssItemRepository

class InsertRssItems (
    private val repository: RssItemRepository
) {
    suspend operator fun invoke(items: List<RssItem>): Unit {
        repository.insertRssItems(items)
    }
}