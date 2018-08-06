package com.cubivue.app.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.cubivue.app.ui.login.LoginViewModel
import com.cubivue.base.di.MyViewModelFactory
import com.cubivue.base.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * All ViewModel classes that uses Dagger2 injection, must be declared here to support constructor injection,
 * otherwise app will give following exception on runtime access:
 * IllegalArgumentException: "unknown model call class"
 */
@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    abstract fun provideViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory
}