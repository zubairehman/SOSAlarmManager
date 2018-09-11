package com.cubivue.base.modules.drawer.fragments

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cubivue.base.BuildConfig
import com.cubivue.base.R
import com.cubivue.base.models.menu.MenuDto
import com.cubivue.base.models.menuAdapter.MenuAdapterBuilder
import com.cubivue.base.models.menuAdapter.MenuAdapterDto
import com.cubivue.base.modules.baseclasses.fragment.BaseFragment
import com.cubivue.base.modules.drawer.adapters.MenuAdapter
import com.cubivue.base.modules.drawer.interfaces.IOnClick
import com.cubivue.base.modules.drawer.interfaces.IOnMenuCreated
import com.cubivue.base.modules.drawer.interfaces.IOnMenuItemClicked
import kotlinx.android.synthetic.main.fragment_abstract_drawer.*
import java.util.*

abstract class AbstractDrawerFragment : BaseFragment(), IOnMenuItemClicked {

    private var adapter: MenuAdapter? = null
    private var menuListener: IOnMenuCreated? = null
    private var clickListener: IOnClick? = null

    //abstract method to accept item layout from client
    open fun getLayout(): MenuAdapterDto {
        return MenuAdapterBuilder()
                .setLayout(R.layout.item_menu)
                .setColumnCount(4)
                .create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_abstract_drawer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setting adapter
        setAdapter(getLayout())

        //setting menu listener
        menuListener = this as IOnMenuCreated
        menuListener!!.onMenuCreated()

        //setting click listener
        clickListener = this as IOnClick

        //setting version number
        txt_version!!.text = "v${BuildConfig.VERSION_NAME}"

        //listeners
        btn_back.setOnClickListener {
            clickListener!!.onClick(it)
        }

        btn_logout.setOnClickListener {
            clickListener!!.onClick(it)
        }
    }

    override fun getExtras(extras: ArrayList<Any>) {

    }

    //Adapter Methods:------------------------------------------------------------------------------
    private fun setAdapter(menuAdapterDto: MenuAdapterDto) {
        adapter = MenuAdapter(activity!!, this, menuAdapterDto.layout)
        rvMenus.setLayoutManager(GridLayoutManager(activity, menuAdapterDto.columnCount))
        rvMenus.setAdapter(adapter)
    }

    fun addMenu(menu: MenuDto) {
        adapter!!.addMenu(menu)
    }

    fun updateMenu(menu: MenuDto) {
        adapter!!.updateMenu(menu)
    }

}
