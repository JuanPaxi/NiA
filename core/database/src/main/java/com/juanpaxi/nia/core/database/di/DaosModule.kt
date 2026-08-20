package com.juanpaxi.nia.core.database.di

import com.juanpaxi.nia.core.database.NiaDatabase
import com.juanpaxi.nia.core.database.dao.NewsResourceDao
import com.juanpaxi.nia.core.database.dao.NewsResourceFtsDao
import com.juanpaxi.nia.core.database.dao.RecentSearchQueryDao
import com.juanpaxi.nia.core.database.dao.TopicDao
import com.juanpaxi.nia.core.database.dao.TopicFtsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun providesTopicsDao(
        database: NiaDatabase,
    ): TopicDao = database.topicDao()

    @Provides
    fun providesNewsResourceDao(
        database: NiaDatabase,
    ): NewsResourceDao = database.newsResourceDao()

    @Provides
    fun providesTopicFtsDao(
        database: NiaDatabase,
    ): TopicFtsDao = database.topicFtsDao()

    @Provides
    fun providesNewsResourceFtsDao(
        database: NiaDatabase,
    ): NewsResourceFtsDao = database.newsResourceFtsDao()

    @Provides
    fun providesRecentSearchQueryDao(
        database: NiaDatabase,
    ): RecentSearchQueryDao = database.recentSearchQueryDao()
}
