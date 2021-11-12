package com.example.rss.domain.model

import org.simpleframework.xml.*

@Root(name = "media:content", strict = false)
data class RssItemMediaContent(
    @field:Attribute(name = "url")
    @param:Attribute(name = "url")
    var url: String? = null,
)