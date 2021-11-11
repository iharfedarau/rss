package com.example.rss.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rss.model.RetrofitInstance
import com.example.rss.model.RssModelItem
import com.example.rss.model.RssModelRootItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class RssItemModel: ViewModel() {
    private var rssItems = MutableLiveData<List<RssModelItem>>()

    val data: LiveData<List<RssModelItem>>
        get() = rssItems

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            val response  = try {
                RetrofitInstance.api.getRssModelRootItem()
            } catch (e: IOException) {
                Log.e("retrofit", "IOException")
            } catch (e: HttpException) {
                Log.e("retrofit", "HttpException")
            }

            if (response != null) {
                rssItems.postValue((response as RssModelRootItem).rssModelItemList)
            }
        }
    }

    public fun refresh() {
        rssItems.value = listOf<RssModelItem>()
        fetchData()
    }
}