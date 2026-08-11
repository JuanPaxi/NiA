package com.juanpaxi.nia.core.network.demo

import JvmUnitTestDemoAssetManager
import com.juanpaxi.nia.core.common.network.Dispatcher
import com.juanpaxi.nia.core.common.network.NiaDispatchers.IO
import com.juanpaxi.nia.core.network.NiaNetworkDataSource
import com.juanpaxi.nia.core.network.model.NetworkTopic
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

class DemoNiaNetworkDataSource @Inject constructor(
    @param:Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json,
    private val assets: DemoAssetManager = JvmUnitTestDemoAssetManager,
) : NiaNetworkDataSource {

    override suspend fun getTopics(ids: List<String>?): List<NetworkTopic> = getDataFromJsonFile(TOPICS_ASSET)

    @OptIn(ExperimentalSerializationApi::class)
    private suspend inline fun <reified T> getDataFromJsonFile(fileName: String): List<T> = withContext(ioDispatcher) {
        assets.open(fileName).use { inputStream ->
            networkJson.decodeFromStream(inputStream)
        }
    }

    companion object {
        private const val TOPICS_ASSET = "topics.json"
    }
}
