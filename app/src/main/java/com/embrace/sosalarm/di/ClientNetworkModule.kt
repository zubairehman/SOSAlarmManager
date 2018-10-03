package com.embrace.sosalarm.di

import com.embrace.sosalarm.data.network.RestService
import com.embrace.soslibrary.di.NetworkModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class ClientNetworkModule() {

    @Provides
    @Singleton
    fun providesRestService(retrofit: Retrofit): RestService = retrofit.create(RestService::class.java)
}