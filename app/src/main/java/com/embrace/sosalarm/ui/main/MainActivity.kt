package com.embrace.sosalarm.ui.main

import android.support.v4.app.Fragment
import com.embrace.sosalarm.ui.main.fragments.MainFragment
import com.embrace.soslibrary.ui.baseclasses.activity.BaseActivity
import java.util.*


class MainActivity : BaseActivity() {
    override fun getBaseFragment(): Fragment {
        return MainFragment()
    }

    override fun getExtras(extras: ArrayList<*>) {
    }

}