package com.embrace.sosalarm.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.embrace.soslibrary.di.MyViewModelFactory
import com.embrace.soslibrary.di.ViewModelKey
import com.embrace.soslibrary.ui.alarm.AlarmViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * All ViewModel classes that uses Dagger2 injection, must be declared here to support constructor injection,
 * otherwise sosalarm will give following exception on runtime access:
 * IllegalArgumentException: "unknown model call class"
 */
@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(AlarmViewModel::class)
    abstract fun bindAlarmViewModel(viewModel: AlarmViewModel): ViewModel

    @Binds
    abstract fun provideViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory
}