package com.juanpaxi.nia.core.database.model

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.Fts4

@Entity(tableName = "topicsFts")
@Fts4
data class TopicFtsEntity(
    @ColumnInfo(name = "topicId")
    val topicId: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "shortDescription")
    val shortDescription: String,
    @ColumnInfo(name = "longDescription")
    val longDescription: String,
)

fun TopicEntity.asFtsEntity() = TopicFtsEntity(
    topicId = id,
    name = name,
    shortDescription = shortDescription,
    longDescription = longDescription,
)
