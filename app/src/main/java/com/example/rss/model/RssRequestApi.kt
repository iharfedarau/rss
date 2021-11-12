package com.example.rss.model

import com.example.rss.domain.model.RssRoot
import retrofit2.http.GET

interface RssRequestApi {
    @GET("/news/rss")
    suspend fun getRssModelRootItem(): RssRoot?
}