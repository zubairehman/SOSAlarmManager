package com.cubivue.app

import android.app.Activity
import android.app.Application
import com.cubivue.app.di.DaggerAppComponent
import com.cubivue.base.util.preference.PreferencesHelper
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber
import javax.inject.Inject

class App : Application() , HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        Realm.init(this)
        Realm.setDefaultConfiguration(realmConfig)

        DaggerAppComponent
                .builder()
                .baseUrl(BuildConfig.BASE_URL)
                .application(this)
                .build()
                .inject(this)

        //init shared preference
        PreferencesHelper.initializeInstance(this)
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