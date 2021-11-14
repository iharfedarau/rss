package com.example.rss.domain.repository

import com.example.rss.domain.model.RssItem
import kotlinx.coroutines.flow.Flow

interface RssItemRepository {
    fun getRssItems(): Flow<List<RssItem>>

    suspend fun getRssItemById(id: Int): RssItem?

    suspend fun insertRssItems(items: List<RssItem>)

    suspend fun clearTable();
}