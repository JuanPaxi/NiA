package com.juanpaxi.nia.core.database.model

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.Fts4

@Entity(tableName = "newsResourcesFts")
@Fts4
data class NewsResourceFtsEntity(
    @ColumnInfo(name = "newsResourceId")
    val newsResourceId: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "content")
    val content: String,
)
