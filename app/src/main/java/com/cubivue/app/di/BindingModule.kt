package com.cubivue.app.di

import com.cubivue.app.ui.login.LoginActivity
import com.cubivue.base.di.ActivityScope
import com.cubivue.base.ui.baseclasses.activity.BaseActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * All components (Activity, Fragment, Services) that have been injected, must be declared here,
 * otherwise app will give exception during run-time.
 *
 * App can give following exceptions during run-time:
 * 1. UninitializedPropertyAccessException: lateinit property has not been initialized
 * 2. IllegalArgumentException: No injector factory bound
 */
@Module
abstract class BindingModule {

    /****************************
     * Activities
     * **************************/

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun baseActivity(): BaseActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun loginActivity(): LoginActivity

    /****************************
     ** Fragments
     ****************************/


    /****************************
     ** Dialogs
     ****************************/

    /****************************
     ** Services
     ****************************/

}