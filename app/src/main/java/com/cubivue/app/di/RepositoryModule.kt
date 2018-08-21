package com.cubivue.app.di

import com.cubivue.app.data.network.RestService
import com.cubivue.app.data.repositories.LoginRepository
import com.cubivue.app.models.job.JobRepositoryImpl
import com.cubivue.base.data.local.RealmHelper
import com.cubivue.base.data.network.BaseRestService
import com.cubivue.base.di.NetworkModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class RepositoryModule {

    @Provides
    @Singleton
    fun provideLoginDataSource(clientApi: RestService, baseApi: BaseRestService): LoginRepository {
        return LoginRepository(clientApi, baseApi)
    }

    @Provides
    @Singleton
    fun provideJobRepository(helper: RealmHelper): JobRepositoryImpl {
        return JobRepositoryImpl(helper)
    }
}