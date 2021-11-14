package com.example.rss.domain.use_case

import com.example.rss.domain.repository.RssItemRepository

class ClearTable (
    private val repository: RssItemRepository
) {
    suspend operator fun invoke(): Unit{
        return repository.clearTable()
    }
}