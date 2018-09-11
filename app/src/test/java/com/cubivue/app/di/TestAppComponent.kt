package com.cubivue.app.di

import com.cubivue.app.TestApp
import com.cubivue.app.data.RestClientTest
import com.cubivue.app.models.job.JobTest
import com.cubivue.app.ui.login.LoginActivityTest
import com.cubivue.base.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Named
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        BindingModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        ClientNetworkModule::class,
        RepositoryModule::class,
        TestAppModule::class
))
interface TestAppComponent : AppComponent {

    @Component.Builder
    interface Builder {

        // bind a `String` named "baseUrl" to this component
        @BindsInstance
        fun baseUrl(@Named("baseUrl") baseUrl: String): TestAppComponent.Builder

        @BindsInstance
        fun testApp(testApp: TestApp): Builder

        fun build(): TestAppComponent
    }

    fun inject(jobTest: JobTest)

    fun inject(activity: LoginActivityTest)

    fun inject(app: TestApp)

    fun inject(restClientTest: RestClientTest)
}