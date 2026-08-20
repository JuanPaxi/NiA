package com.juanpaxi.nia.core.database

import androidx.room3.ColumnTypeConverters
import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.juanpaxi.nia.core.database.dao.NewsResourceDao
import com.juanpaxi.nia.core.database.dao.NewsResourceFtsDao
import com.juanpaxi.nia.core.database.dao.RecentSearchQueryDao
import com.juanpaxi.nia.core.database.dao.TopicDao
import com.juanpaxi.nia.core.database.dao.TopicFtsDao
import com.juanpaxi.nia.core.database.model.NewsResourceEntity
import com.juanpaxi.nia.core.database.model.NewsResourceFtsEntity
import com.juanpaxi.nia.core.database.model.NewsResourceTopicCrossRef
import com.juanpaxi.nia.core.database.model.RecentSearchQueryEntity
import com.juanpaxi.nia.core.database.model.TopicEntity
import com.juanpaxi.nia.core.database.model.TopicFtsEntity
import com.juanpaxi.nia.core.database.util.InstantConverter

@Database(
    entities = [
        NewsResourceEntity::class,
        NewsResourceTopicCrossRef::class,
        NewsResourceFtsEntity::class,
        TopicEntity::class,
        TopicFtsEntity::class,
        RecentSearchQueryEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
@ColumnTypeConverters(
    InstantConverter::class,
)
internal abstract class NiaDatabase : RoomDatabase() {
    abstract fun topicDao(): TopicDao
    abstract fun newsResourceDao(): NewsResourceDao
    abstract fun topicFtsDao(): TopicFtsDao
    abstract fun newsResourceFtsDao(): NewsResourceFtsDao
    abstract fun recentSearchQueryDao(): RecentSearchQueryDao
}
