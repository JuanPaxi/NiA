package com.juanpaxi.nia.core.network.demo

import JvmUnitTestDemoAssetManager
import com.juanpaxi.nia.core.common.network.Dispatcher
import com.juanpaxi.nia.core.common.network.NiaDispatchers.IO
import com.juanpaxi.nia.core.network.NiaNetworkDataSource
import com.juanpaxi.nia.core.network.model.NetworkChangeList
import com.juanpaxi.nia.core.network.model.NetworkNewsResource
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

    override suspend fun getNewsResources(ids: List<String>?): List<NetworkNewsResource> = getDataFromJsonFile(NEWS_ASSET)

    override suspend fun getTopicChangeList(after: Int?): List<NetworkChangeList> = getTopics().mapToChangeList(NetworkTopic::id)

    override suspend fun getNewsResourceChangeList(after: Int?): List<NetworkChangeList> = getNewsResources().mapToChangeList(NetworkNewsResource::id)

    @OptIn(ExperimentalSerializationApi::class)
    private suspend inline fun <reified T> getDataFromJsonFile(fileName: String): List<T> = withContext(ioDispatcher) {
        assets.open(fileName).use { inputStream ->
            networkJson.decodeFromStream(inputStream)
        }
    }

    companion object {
        private const val NEWS_ASSET = "news.json"
        private const val TOPICS_ASSET = "topics.json"
    }
}

private fun <T> List<T>.mapToChangeList(
    idGetter: (T) -> String,
) = mapIndexed { index, item ->
    NetworkChangeList(
        id = idGetter(item),
        changeListVersion = index,
        isDelete = false,
    )
}
