package com.example.rss.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rss.domain.model.RssItem
import kotlinx.coroutines.flow.Flow


@Dao
interface RssItemDao {
    @Query("SELECT * FROM rssitem")
    fun getRssItems(): Flow<List<RssItem>>

    @Query("SELECT * FROM rssitem WHERE id = :id")
    suspend fun getRssItemById(id: Int): RssItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRssItems(items: List<RssItem>)
}