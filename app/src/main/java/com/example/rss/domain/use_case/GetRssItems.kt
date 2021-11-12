package com.example.rss.domain.use_case

import com.example.rss.domain.model.RssItem
import com.example.rss.domain.repository.RssItemRepository
import kotlinx.coroutines.flow.Flow

class GetRssItems (
    private val repository: RssItemRepository
) {
    operator fun invoke(): Flow<List<RssItem>> {
        return repository.getRssItems()
    }
}