package com.example.rss.domain.model

import org.simpleframework.xml.*

@Root(name = "rss", strict = false)
class RssRoot(
    @field:Element(name = "title")
    @param:Element(name = "title")
    @field:Path("channel")
    @param:Path("channel")
    val channelTitle: String? = null,

    @field:ElementList(name = "item", inline = true, required = false)
    @param:ElementList(name = "item", inline = true, required = false)
    @field:Path("channel")
    @param:Path("channel")
    val rssItemList: List<RssItem>? =  null
)