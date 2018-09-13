package com.cubivue.app.di

import com.cubivue.app.ui.home.HomeActivity
import com.cubivue.app.ui.home.fragments.DrawerFragment
import com.cubivue.app.ui.home.fragments.EmptyFragment
import com.cubivue.app.ui.login.LoginActivity
import com.cubivue.app.ui.login.fragments.LoginFragment
import com.cubivue.app.ui.splash.SplashActivity
import com.cubivue.app.ui.splash.fragments.SplashFragment
import com.cubivue.base.di.ActivityScope
import com.cubivue.base.di.FragmentScope
import com.cubivue.base.ui.baseclasses.activity.BaseActivity
import com.cubivue.base.ui.baseclasses.activity.BaseDrawerActivity
import com.cubivue.base.ui.baseclasses.fragment.BaseFragment
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
    abstract fun baseDrawerActivity(): BaseDrawerActivity

    /* @ActivityScope
     @ContributesAndroidInjector
     abstract fun baseLoginActivity(): BaseLoginActivity

     @ActivityScope
     @ContributesAndroidInjector
     abstract fun baseToolbarActivity(): BaseToolbarActivity*/

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun loginActivity(): LoginActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun splashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun homeActivity(): HomeActivity

    /****************************
     ** Fragments
     ****************************/

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun baseFragment(): BaseFragment

    /*@FragmentScope
    @ContributesAndroidInjector
    abstract fun baseToolbarFragment(): BaseToolbarFragment*/

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun splashFragment(): SplashFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun loginFragment(): LoginFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun emptyFragment(): EmptyFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun drawerFragment(): DrawerFragment

    /****************************
     ** Dialogs
     ****************************/

    /****************************
     ** Services
     ****************************/

}