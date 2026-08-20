package com.juanpaxi.nia.core.database

import android.content.Context
import androidx.room3.Room
import androidx.test.core.app.ApplicationProvider
import com.juanpaxi.nia.core.database.dao.NewsResourceDao
import com.juanpaxi.nia.core.database.dao.TopicDao
import org.junit.After
import org.junit.Before

internal abstract class DatabaseTest {

    private lateinit var db: NiaDatabase
    protected lateinit var newsResourceDao: NewsResourceDao
    protected lateinit var topicDao: TopicDao

    @Before
    fun setup() {
        db = run {
            val context = ApplicationProvider.getApplicationContext<Context>()
            Room.inMemoryDatabaseBuilder(
                context,
                NiaDatabase::class.java,
            ).build()
        }
        newsResourceDao = db.newsResourceDao()
        topicDao = db.topicDao()
    }

    @After
    fun teardown() = db.close()
}
