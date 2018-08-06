package com.cubivue.base.di

import com.michaelflisar.rxbus2.RxBus
import com.michaelflisar.rxbus2.RxBusSenderBuilder
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Module(includes = [AndroidInjectionModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideRxBus(): RxBusSenderBuilder {
        return RxBus.get()
    }
}