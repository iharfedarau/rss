package com.example.rss.domain.use_case

import com.example.rss.domain.model.RssItem
import com.example.rss.domain.repository.RssItemRepository
import kotlinx.coroutines.flow.Flow

class ClearTable (
    private val repository: RssItemRepository
) {
    suspend operator fun invoke(): Unit{
        return repository.clearTable()
    }
}