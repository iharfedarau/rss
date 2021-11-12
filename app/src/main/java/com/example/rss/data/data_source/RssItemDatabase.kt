package com.example.rss.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rss.domain.model.Converters
import com.example.rss.domain.model.RssItem

@Database(
    entities = [RssItem::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RssItemDatabase: RoomDatabase() {
    abstract val rssItemDao: RssItemDao

    companion object {
        const val DATABASE_NAME = "rssitems"
    }
}