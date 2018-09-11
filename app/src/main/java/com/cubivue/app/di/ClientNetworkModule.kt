package com.cubivue.app.di

import com.cubivue.app.data.network.RestService
import com.cubivue.base.di.NetworkModule
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