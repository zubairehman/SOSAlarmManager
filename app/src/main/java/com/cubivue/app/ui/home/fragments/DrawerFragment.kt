package com.cubivue.app.ui.home.fragments

import android.view.View
import com.cubivue.app.R
import com.cubivue.base.models.menu.MenuBuilder
import com.cubivue.base.models.menu.MenuDto
import com.cubivue.base.models.menuAdapter.MenuAdapterBuilder
import com.cubivue.base.models.menuAdapter.MenuAdapterDto
import com.cubivue.base.ui.baseclasses.activity.BaseDrawerActivity
import com.cubivue.base.ui.drawer.fragments.BaseDrawerFragment
import com.cubivue.base.ui.drawer.interfaces.IOnClick
import com.cubivue.base.ui.drawer.interfaces.IOnMenuCreated


class DrawerFragment : BaseDrawerFragment(), IOnMenuCreated, IOnClick {

    private var items: List<MenuDto>? = null

    override fun getLayout(): MenuAdapterDto {
        return MenuAdapterBuilder()
                .setLayout(layout = R.layout.item_child_menu)
                .setColumnCount(columnCount = 4)
                .create()
    }

    override fun onMenuCreated() {
        super.onMenuCreated()

        //create menu
        val menuDto = MenuBuilder()
                .setName("MenuItem Name") //MenuItem name
                .setImageRes(R.drawable.ic_launcher_foreground) //MenuItem image
                .setIsActive(true) //Attribute to enable/disable menu item
                .setIsCounterVisible(true) //Attribute to show counter
                .setCounterValue(4) //Counter calue
                .create()

        addMenu(menuDto)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_back) {
            (activity as BaseDrawerActivity).closeDrawer()
        } else if (view.id == R.id.btn_logout) {
            (activity as BaseDrawerActivity).closeDrawer()
        }
    }

    override fun onItemClick(items: List<MenuDto>, position: Int) {
        super.onItemClick(items, position)
        this.items = items
        (activity as BaseDrawerActivity).closeDrawer()

        //update menu
        updateMenu(position)
    }

    fun updateMenu(position: Int) {
        //in-order to update menu item
        val menuDto = items!!.get(position)
        menuDto.counterValue = 10
        menuDto.isCounterVisible = true
        updateMenu(menuDto)
    }
}
