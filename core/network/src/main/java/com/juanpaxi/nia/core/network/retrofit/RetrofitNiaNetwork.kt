package com.juanpaxi.nia.core.network.retrofit

import com.juanpaxi.nia.core.network.BuildConfig
import com.juanpaxi.nia.core.network.NiaNetworkDataSource
import com.juanpaxi.nia.core.network.model.NetworkTopic
import dagger.Lazy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitNiaNetworkApi {

    @GET(value = "topics")
    suspend fun getTopics(
        @Query("id") ids: List<String>?,
    ): NetworkResponse<List<NetworkTopic>>
}

private const val NIA_BASE_URL = BuildConfig.BACKEND_URL

@Serializable
private data class NetworkResponse<T>(
    val data: T,
)

@Singleton
internal class RetrofitNiaNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Lazy<Call.Factory>,
) : NiaNetworkDataSource {

    private val networkApi = Retrofit
        .Builder()
        .baseUrl(NIA_BASE_URL)
        .callFactory { okhttpCallFactory.get().newCall(it) }
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(RetrofitNiaNetworkApi::class.java)

    override suspend fun getTopics(ids: List<String>?): List<NetworkTopic> = networkApi.getTopics(ids = ids).data
}
