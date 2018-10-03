package com.embrace.sosalarm.di

import com.embrace.sosalarm.ui.main.MainActivity
import com.embrace.sosalarm.ui.main.fragments.MainFragment
import com.embrace.soslibrary.di.ActivityScope
import com.embrace.soslibrary.di.FragmentScope
import com.embrace.soslibrary.ui.alarm.AlarmActivity
import com.embrace.soslibrary.ui.baseclasses.activity.BaseActivity
import com.embrace.soslibrary.ui.baseclasses.activity.BaseDrawerActivity
import com.embrace.soslibrary.ui.baseclasses.fragment.BaseFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * All components (Activity, Fragment, Services) that have been injected, must be declared here,
 * otherwise sosalarm will give exception during run-time.
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
    abstract fun loginActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun alarmActivity(): AlarmActivity


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
    abstract fun loginFragment(): MainFragment

    /****************************
     ** Dialogs
     ****************************/

    /****************************
     ** Services
     ****************************/

}