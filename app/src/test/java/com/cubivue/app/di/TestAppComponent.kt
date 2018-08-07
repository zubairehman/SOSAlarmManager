package com.cubivue.app.di

import com.cubivue.app.TestApp
import com.cubivue.base.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        BindingModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        TestAppModule::class
))
internal interface TestAppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun testApp(testApp: TestApp): Builder

        fun build(): TestAppComponent
    }

    fun inject(app: TestApp)
}