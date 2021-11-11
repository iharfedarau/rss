package com.example.rss.model

import org.simpleframework.xml.*

@Root(name = "channel", strict = false)
class RssModelChannelItem @JvmOverloads constructor(
    @field: ElementList(inline = true)
    var rssModelItemList: List<RssModelItem>? = null
)
