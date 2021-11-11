package com.example.rss.model

import retrofit2.http.GET

interface RssRequestApi {
    @GET("/news/rss")
    suspend fun getRssModelRootItem(): RssModelRootItem?
}