package com.example.rss.data.repository

import com.example.rss.data.data_source.RssItemDao
import com.example.rss.domain.model.RssItem
import com.example.rss.domain.repository.RssItemRepository
import kotlinx.coroutines.flow.Flow

class RssItemRepositoryImpl(
    private val dao: RssItemDao
): RssItemRepository {
    override fun getRssItems(): Flow<List<RssItem>> {
        return dao.getRssItems()
    }

    override suspend fun getRssItemById(id: Int): RssItem? {
        return dao.getRssItemById(id)
    }

    override suspend fun insertRssItems(items: List<RssItem>) {
        dao.insertRssItems(items)
    }

    override suspend fun clearTable() {
        dao.clearTable()
    }
}