package com.juanpaxi.nia.core.database.model

import androidx.room3.Embedded
import androidx.room3.Junction
import androidx.room3.Relation
import com.juanpaxi.nia.core.model.data.NewsResource

data class PopulatedNewsResource(
    @Embedded
    val entity: NewsResourceEntity,
    @Relation(
        parentColumns = ["id"],
        entityColumns = ["id"],
        associateBy = Junction(
            value = NewsResourceTopicCrossRef::class,
            parentColumns = ["news_resource_id"],
            entityColumns = ["topic_id"],
        ),
    )
    val topics: List<TopicEntity>,
)

fun PopulatedNewsResource.asExternalModel() = NewsResource(
    id = entity.id,
    title = entity.title,
    content = entity.content,
    url = entity.url,
    headerImageUrl = entity.headerImageUrl,
    publishDate = entity.publishDate,
    type = entity.type,
    topics = topics.map(TopicEntity::asExternalModel),
)

fun PopulatedNewsResource.asFtsEntity() = NewsResourceFtsEntity(
    newsResourceId = entity.id,
    title = entity.title,
    content = entity.content,
)
