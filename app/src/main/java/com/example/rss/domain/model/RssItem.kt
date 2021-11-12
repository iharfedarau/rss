package com.example.rss.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.simpleframework.xml.*

@Entity
@Root(name = "item", strict = false)
data class RssItem(
    @field:Element(name = "title")
    @param:Element(name = "title")
    val title: String? = null,

    @field:Element(name = "link")
    @param:Element(name = "link")
    val link: String? = null,

    @field:Element(name = "description", required = false)
    @param:Element(name = "description", required = false)
    val description: String? = null,

    @field:Element(name = "content", required = false)
    @param:Element(name = "content", required = false)
    val mediaContentMediaContent: RssItemMediaContent? = null,

    @PrimaryKey
    @field:Element(name = "id", required = false)
    @param:Element(name = "id", required = false)
    val id: Int? = null,
)
