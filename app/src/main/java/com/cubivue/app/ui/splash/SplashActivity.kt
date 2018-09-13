package com.cubivue.app.ui.splash

import android.support.v4.app.Fragment
import com.cubivue.app.ui.splash.fragments.SplashFragment
import com.cubivue.base.ui.baseclasses.activity.BaseActivity
import java.util.*


class SplashActivity : BaseActivity() {

    override fun getBaseFragment(): Fragment {
        return SplashFragment()
    }

    override fun getExtras(extras: ArrayList<*>) {
    }
}