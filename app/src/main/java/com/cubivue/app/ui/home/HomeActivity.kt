package com.cubivue.app.ui.home

import android.support.v4.app.Fragment
import com.cubivue.app.ui.home.fragments.DrawerFragment
import com.cubivue.app.ui.home.fragments.EmptyFragment
import com.cubivue.base.ui.baseclasses.activity.BaseDrawerActivity
import java.util.*

class HomeActivity : BaseDrawerActivity() {

    override fun getBaseFragment(): Fragment {
        return EmptyFragment()
    }

    override fun getExtras(extras: ArrayList<*>) {
    }

    override fun getDrawerFragment(): Fragment {
        return DrawerFragment()
    }
}
