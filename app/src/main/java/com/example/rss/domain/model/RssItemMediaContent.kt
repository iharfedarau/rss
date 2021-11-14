package com.example.rss.domain.model

import androidx.room.TypeConverter
import org.simpleframework.xml.*

@Root(name = "media:content", strict = false)
data class RssItemMediaContent(
    @field:Attribute(name = "url")
    @param:Attribute(name = "url")
    var url: String? = null,
)

class Converters {
    @TypeConverter
    fun fromRssItemMediaContent(value: RssItemMediaContent?): String? {
        if (value == null) {
            return null
        }
        return value.url
    }

    @TypeConverter
    fun toRssItemMediaContent(url: String?): RssItemMediaContent? {
        if (url == null) {
            return null
        }
        return RssItemMediaContent(url)
    }
}