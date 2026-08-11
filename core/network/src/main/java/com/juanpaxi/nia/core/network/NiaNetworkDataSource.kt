package com.juanpaxi.nia.core.network

import com.juanpaxi.nia.core.network.model.NetworkTopic

interface NiaNetworkDataSource {

    suspend fun getTopics(ids: List<String>? = null): List<NetworkTopic>
}
