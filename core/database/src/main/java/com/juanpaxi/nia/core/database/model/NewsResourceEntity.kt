package com.juanpaxi.nia.core.database.model

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.PrimaryKey
import com.juanpaxi.nia.core.model.data.NewsResource
import kotlin.time.Instant

@Entity(
    tableName = "news_resources",
)
data class NewsResourceEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val content: String,
    val url: String,
    @ColumnInfo(name = "header_image_url")
    val headerImageUrl: String?,
    @ColumnInfo(name = "publish_date")
    val publishDate: Instant,
    val type: String,
)

fun NewsResourceEntity.asExternalModel() = NewsResource(
    id = id,
    title = title,
    content = content,
    url = url,
    headerImageUrl = headerImageUrl,
    publishDate = publishDate,
    type = type,
    topics = emptyList(),
)
