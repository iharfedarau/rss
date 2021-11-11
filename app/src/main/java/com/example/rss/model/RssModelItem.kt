package com.example.rss.model

import org.simpleframework.xml.*

@Root(name = "item", strict = false)
data class RssModelItem @JvmOverloads constructor(

    @field:Element(name = "title")
    @param:Element(name = "title")
    var title: String? = null,

    @field:Element(name = "link")
    @param:Element(name = "link")
    var link: String? = null,

    @field:Element(name = "description", required = false)
    @param:Element(name = "description", required = false)
    var description: String? = null,

    @field:Element(name = "content", required = false)
    @param:Element(name = "content", required = false)
    var mediaContent: RssModelMediaContentItem? = null
)
