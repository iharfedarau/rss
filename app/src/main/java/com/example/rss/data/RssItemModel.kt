package com.example.rss.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class RssItemModel: ViewModel() {
    private var rssItems = MutableLiveData<List<RssItem>>()

    val data: LiveData<List<RssItem>>
        get() = rssItems

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            val connection = URL("https://feeds.simplecast.com/54nAGcIl").openConnection() as HttpURLConnection
            connection.readTimeout = 8000
            connection.connectTimeout = 8000
            connection.requestMethod = "GET"
            connection.connect();

            val responseCode: Int = connection.responseCode;
            if (responseCode == 200) {
                val stream: InputStream = connection.inputStream;
                try {
                    val parser = RssParser()
                    rssItems.postValue(parser.parse(stream!!))
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}