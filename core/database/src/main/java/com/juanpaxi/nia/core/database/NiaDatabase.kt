package com.juanpaxi.nia.core.database

import androidx.room3.Database
import androidx.room3.RoomDatabase
import com.juanpaxi.nia.core.database.dao.TopicDao
import com.juanpaxi.nia.core.database.model.TopicEntity

@Database(
    entities = [
        TopicEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
internal abstract class NiaDatabase : RoomDatabase() {

    abstract fun topicDao(): TopicDao
}
