package com.cubivue.app.di

import com.cubivue.app.App
import com.cubivue.base.di.AppModule
import com.cubivue.base.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        BindingModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        ClientNetworkModule::class,
        RepositoryModule::class,
        ClientAppModule::class
))
interface AppComponent {

    @Component.Builder
    interface Builder {

        // bind a `String` named "baseUrl" to this component
        @BindsInstance
        fun baseUrl(@Named("baseUrl") baseUrl: String): Builder

        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}