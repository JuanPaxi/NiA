package com.juanpaxi.nia.core.database.model

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.juanpaxi.nia.core.model.data.Topic

@Entity(tableName = "topics")
data class TopicEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val shortDescription: String,
    @ColumnInfo(defaultValue = "")
    val longDescription: String,
    @ColumnInfo(defaultValue = "")
    val url: String,
    @ColumnInfo(defaultValue = "")
    val imageUrl: String,
)

fun TopicEntity.asExternalModel() = Topic(
    id = id,
    name = name,
    shortDescription = shortDescription,
    longDescription = longDescription,
    url = url,
    imageUrl = imageUrl,
)
