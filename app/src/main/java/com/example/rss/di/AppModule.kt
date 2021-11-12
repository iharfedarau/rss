package com.example.rss.di

import android.app.Application
import androidx.room.*
import com.example.rss.data.data_source.RssItemDao
import com.example.rss.data.data_source.RssItemDatabase
import com.example.rss.data.repository.RssItemRepositoryImpl
import com.example.rss.domain.model.RssItem
import com.example.rss.domain.repository.RssItemRepository
import com.example.rss.domain.use_case.GetRssItems
import com.example.rss.domain.use_case.InsertRssItems
import com.example.rss.domain.use_case.RssItemUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import org.simpleframework.xml.Root
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRssItemDatabase(app: Application): RssItemDatabase {
        return Room.databaseBuilder(
            app,
            RssItemDatabase::class.java,
            RssItemDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRssItemRepository(db: RssItemDatabase): RssItemRepository {
        return RssItemRepositoryImpl(db.rssItemDao)
    }

    @Provides
    @Singleton
    fun provideRssItemUseCases(repository: RssItemRepository): RssItemUseCases{
        return RssItemUseCases(
            getRssItems = GetRssItems(repository),
            insertRssItems =  InsertRssItems(repository)
        )
    }
}