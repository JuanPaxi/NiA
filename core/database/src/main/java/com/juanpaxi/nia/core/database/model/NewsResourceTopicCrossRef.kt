package com.juanpaxi.nia.core.database.model

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index

@Entity(
    tableName = "news_resources_topics",
    primaryKeys = ["news_resource_id", "topic_id"],
    foreignKeys = [
        ForeignKey(
            entity = NewsResourceEntity::class,
            parentColumns = ["id"],
            childColumns = ["news_resource_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = TopicEntity::class,
            parentColumns = ["id"],
            childColumns = ["topic_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = ["news_resource_id"]),
        Index(value = ["topic_id"]),
    ],
)
data class NewsResourceTopicCrossRef(
    @ColumnInfo(name = "news_resource_id")
    val newsResourceId: String,
    @ColumnInfo(name = "topic_id")
    val topicId: String,
)
