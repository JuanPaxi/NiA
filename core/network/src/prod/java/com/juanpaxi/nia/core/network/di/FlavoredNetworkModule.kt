package com.juanpaxi.nia.core.network.di

import com.juanpaxi.nia.core.network.NiaNetworkDataSource
import com.juanpaxi.nia.core.network.retrofit.RetrofitNiaNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface FlavoredNetworkModule {

    @Binds
    fun binds(impl: RetrofitNiaNetwork): NiaNetworkDataSource
}
