package com.juanpaxi.nia.core.network.model

import com.juanpaxi.nia.core.model.data.Topic
import kotlinx.serialization.Serializable

@Serializable
data class NetworkTopic(
    val id: String,
    val name: String = "",
    val shortDescription: String = "",
    val longDescription: String = "",
    val url: String = "",
    val imageUrl: String = "",
    val followed: Boolean = false,
)

fun NetworkTopic.asExternalModel(): Topic = Topic(
    id = id,
    name = name,
    shortDescription = shortDescription,
    longDescription = longDescription,
    url = url,
    imageUrl = imageUrl,
)
