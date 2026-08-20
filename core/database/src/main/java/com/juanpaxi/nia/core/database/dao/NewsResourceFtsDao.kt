package com.juanpaxi.nia.core.database.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import com.juanpaxi.nia.core.database.model.NewsResourceFtsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsResourceFtsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(newsResources: List<NewsResourceFtsEntity>)

    @Query("SELECT newsResourceId FROM newsResourcesFts WHERE newsResourcesFts MATCH :query")
    fun searchAllNewsResources(query: String): Flow<List<String>>

    @Query("SELECT count(*) FROM newsResourcesFts")
    fun getCount(): Flow<Int>
}
