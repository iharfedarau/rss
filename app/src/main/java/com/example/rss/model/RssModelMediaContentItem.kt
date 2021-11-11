package com.example.rss.model

import org.simpleframework.xml.*

@Root(name = "media:content", strict = false)
data class RssModelMediaContentItem @JvmOverloads constructor(
    @field:Attribute(name = "url")
    @param:Attribute(name = "url")
    var url: String? = null,
)