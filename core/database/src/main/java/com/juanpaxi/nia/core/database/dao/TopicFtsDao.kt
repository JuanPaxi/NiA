package com.juanpaxi.nia.core.database.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import com.juanpaxi.nia.core.database.model.TopicFtsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopicFtsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(topics: List<TopicFtsEntity>)

    @Query("SELECT topicId FROM topicsFts WHERE topicsFts MATCH :query")
    fun searchAllTopics(query: String): Flow<List<String>>

    @Query("SELECT count(*) FROM topicsFts")
    fun getCount(): Flow<Int>
}
