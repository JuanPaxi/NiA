package com.juanpaxi.nia.core.network.di

import android.content.Context
import coil3.ImageLoader
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.svg.SvgDecoder
import coil3.util.DebugLogger
import com.juanpaxi.nia.core.network.BuildConfig
import com.juanpaxi.nia.core.network.demo.DemoAssetManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providesDemoAssetManager(
        @ApplicationContext context: Context,
    ): DemoAssetManager = DemoAssetManager(context.assets::open)

    @Provides
    @Singleton
    fun provideOkHttpCallFactory(): Call.Factory = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .apply {
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                },
        )
        .build()

    @Provides
    @Singleton
    fun imageLoader(
        okHttpCallFactory: Lazy<Call.Factory>,
        @ApplicationContext application: Context,
    ): ImageLoader = ImageLoader
        .Builder(application)
        .components {
            add(OkHttpNetworkFetcherFactory(callFactory = { OkHttpClient() }))
            add(SvgDecoder.Factory())
        }
        .apply {
            if (BuildConfig.DEBUG) logger(DebugLogger())
        }
        .build()
}
