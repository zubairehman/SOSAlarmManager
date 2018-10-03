package com.embrace.sosalarm.di

import com.embrace.soslibrary.data.network.BaseRestService
import com.embrace.soslibrary.data.repositories.AlarmRepository
import com.embrace.soslibrary.di.NetworkModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAlarmDataSource(baseApi: BaseRestService): AlarmRepository {
        return AlarmRepository(baseApi)
    }

}