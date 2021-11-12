package com.example.rss.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rss.domain.model.RssItem

@Database(
    entities = [RssItem::class],
    version = 1,
    exportSchema = false
)
abstract class RssItemDatabase: RoomDatabase() {
    abstract val rssItemDao: RssItemDao

    companion object {
        const val DATABASE_NAME = "rssitems"
    }
}