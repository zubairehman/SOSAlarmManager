package com.cubivue.app

import android.app.Activity
import android.app.Application
import com.cubivue.app.di.AppComponent
import com.cubivue.app.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.realm.RealmConfiguration
import timber.log.Timber
import javax.inject.Inject


open class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    private var mApplicationComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        //Realm.init(this)
        //Realm.setDefaultConfiguration(realmConfig)

        createAppComponent()
    }

    open fun createAppComponent(): AppComponent? {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerAppComponent.builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .application(this)
                    .build()
                    .also {
                        it.inject(this)
                    }

        }
        return mApplicationComponent
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }

    val realmConfig: RealmConfiguration
        get() = RealmConfiguration.Builder()
                .name(BuildConfig.DB_NAME)
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build()

}