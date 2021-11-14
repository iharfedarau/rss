package com.example.rss.model

import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object RetrofitInstance {
    val api: RssRequestApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.yahoo.com")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
            .create(RssRequestApi::class.java)
    }
}