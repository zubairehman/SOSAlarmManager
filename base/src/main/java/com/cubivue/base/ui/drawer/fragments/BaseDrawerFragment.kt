package com.cubivue.base.ui.drawer.fragments

import com.cubivue.base.R
import com.cubivue.base.models.menu.MenuBuilder
import com.cubivue.base.models.menu.MenuDto
import com.cubivue.base.ui.drawer.interfaces.IOnMenuCreated


open class BaseDrawerFragment : AbstractDrawerFragment(), IOnMenuCreated {

    override fun onMenuCreated() {
        for (i in 0..4) {
            val menuDto = MenuBuilder()
                    .setName("Base MenuItem" + 1)
                    .setImageRes(R.drawable.ic_launcher_foreground)
                    .setIsActive(true)
                    .create()
            addMenu(menuDto)
        }
    }

    override fun onItemClick(items: List<MenuDto>, position: Int) {

    }

}
