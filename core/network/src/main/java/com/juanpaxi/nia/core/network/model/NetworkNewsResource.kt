package com.juanpaxi.nia.core.network.model

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class NetworkNewsResource(
    val id: String,
    val title: String,
    val content: String,
    val url: String,
    val headerImageUrl: String,
    val publishDate: Instant,
    val type: String,
    val topics: List<String> = emptyList(),
)
