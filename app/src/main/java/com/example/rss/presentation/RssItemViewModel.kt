package com.example.rss.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rss.domain.model.RssItem
import com.example.rss.domain.model.RssRoot
import com.example.rss.domain.use_case.RssItemUseCases
import com.example.rss.model.RetrofitInstance
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.seconds
import kotlinx.coroutines.flow.*

@HiltViewModel
class RssItemViewModel @Inject constructor(
    private val rssItemUseCases: RssItemUseCases
): ViewModel() {
    private var getRssItemsJob: Job? = null

    private var rssItems = MutableLiveData<List<RssItem>>()
    val data: LiveData<List<RssItem>> = rssItems

    init {
        getRssItems()
    }

    private fun fetchData(onRefresFinished: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            rssItemUseCases.clearTable()

            val response  = try {
                RetrofitInstance.api.getRssModelRootItem()
            } catch (e: IOException) {
                Log.e("retrofit", "IOException")
            } catch (e: HttpException) {
                Log.e("retrofit", "HttpException")
            }

            launch(Dispatchers.Main) {
                onRefresFinished()
            }

            if (response != null) {
                val rssItems = (response as RssRoot).rssItemList
                if (rssItems != null) {
                    rssItemUseCases.insertRssItems(rssItems)
                }
            }
        }
    }

    public fun refresh(onRefresFinished: suspend () -> Unit) {
        rssItems.value = listOf<RssItem>()
        fetchData(onRefresFinished)
    }

    private fun getRssItems() {
        getRssItemsJob?.cancel()
        getRssItemsJob = viewModelScope.launch {
             rssItemUseCases.getRssItems().collect {
                 rssItems.value = it
            }
        }
    }
}