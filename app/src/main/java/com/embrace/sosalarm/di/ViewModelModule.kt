package com.embrace.sosalarm.di

import android.arch.lifecycle.ViewModelProvider
import com.embrace.soslibrary.di.MyViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * All ViewModel classes that uses Dagger2 injection, must be declared here to support constructor injection,
 * otherwise sosalarm will give following exception on runtime access:
 * IllegalArgumentException: "unknown model call class"
 */
@Module
abstract class ViewModelModule {
    @Binds
    abstract fun provideViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory
}