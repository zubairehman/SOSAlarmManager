package com.cubivue.app.ui.home.activities

import android.support.v4.app.Fragment
import com.cubivue.app.ui.home.fragments.DrawerFragment
import com.cubivue.app.ui.home.fragments.EmptyFragment
import com.cubivue.base.ui.baseclasses.activity.BaseDrawerActivity
import java.util.*

class DashboardActivity : BaseDrawerActivity() {

    override fun getBaseFragment(): Fragment {
        return EmptyFragment()
    }

    override fun getExtras(extras: ArrayList<*>) {
    }

    override fun getDrawerFragment(): Fragment {
        return DrawerFragment()
    }
}
