package com.cubivue.app.di

import com.cubivue.app.data.repositories.LoginRepository
import com.cubivue.base.di.NetworkModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class RepositoryModule {

    @Provides
    @Singleton
    fun provideLoginDataSource(/*api: RestService, db: RealmHelper*/): LoginRepository {
        return LoginRepository(/*api,db*/)
    }

}