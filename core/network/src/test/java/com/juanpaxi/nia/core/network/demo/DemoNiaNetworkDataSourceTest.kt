package com.juanpaxi.nia.core.network.demo

import JvmUnitTestDemoAssetManager
import com.juanpaxi.nia.core.network.model.NetworkTopic
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DemoNiaNetworkDataSourceTest {

    private lateinit var subject: DemoNiaNetworkDataSource
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        subject = DemoNiaNetworkDataSource(
            ioDispatcher = testDispatcher,
            networkJson = Json { ignoreUnknownKeys = true },
            assets = JvmUnitTestDemoAssetManager,
        )
    }

    @Suppress("ktlint:standard:max-line-length")
    @Test
    fun testDeserializationOfTopics() = runTest(testDispatcher) {
        assertEquals(
            NetworkTopic(
                id = "1",
                name = "Headlines",
                shortDescription = "News you'll definitely be interested in",
                longDescription = "The latest events and announcements from the world of Android development.",
                url = "",
                imageUrl = "https://firebasestorage.googleapis.com/v0/b/now-in-android.appspot.com/o/img%2Fic_topic_Headlines.svg?alt=media&token=506faab0-617a-4668-9e63-4a2fb996603f",
            ),
            subject.getTopics().first(),
        )
    }
}
