package com.cubivue.app.di

import com.cubivue.base.data.local.RealmHelper
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Module(includes = [AndroidInjectionModule::class])
class TestAppModule {


    @Provides
    @Singleton
    fun provideRealmHelper(): RealmHelper {
        return RealmHelper()
    }
}