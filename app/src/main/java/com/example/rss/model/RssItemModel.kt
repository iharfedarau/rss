package com.example.rss.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class RssItemModel: ViewModel() {
    private var rssItems = MutableLiveData<List<RssModelItem>>()

    val data: LiveData<List<RssModelItem>>
        get() = rssItems

    init {
        fetchData{}
    }

    private fun fetchData(onRefresFinished: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val response  = try {
                RetrofitInstance.api.getRssModelRootItem()
            } catch (e: IOException) {
                Log.e("retrofit", "IOException")
            } catch (e: HttpException) {
                Log.e("retrofit", "HttpException")
            }

            launch (Dispatchers.Main){
                rssItems.value = (response as RssModelRootItem).rssModelItemList
                onRefresFinished()
            }
        }
    }

    public fun refresh(onRefresFinished: suspend () -> Unit) {
        rssItems.value = listOf<RssModelItem>()
        fetchData(onRefresFinished)
    }
}