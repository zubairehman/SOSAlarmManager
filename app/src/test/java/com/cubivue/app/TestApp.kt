package com.cubivue.app

import android.app.Activity
import com.cubivue.app.di.DaggerTestAppComponent
import com.cubivue.app.di.TestAppComponent
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector

class TestApp : App(), HasActivityInjector {

    private var testApplicationComponent: TestAppComponent? = null


    override fun createAppComponent(): TestAppComponent? {
        if (testApplicationComponent == null) {
            testApplicationComponent = DaggerTestAppComponent.builder()
                    .testApp(this)
                    .baseUrl(BuildConfig.BASE_URL)
                    .build()

        }
        return testApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }
}