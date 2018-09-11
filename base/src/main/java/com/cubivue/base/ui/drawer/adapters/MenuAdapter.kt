package com.cubivue.base.ui.drawer.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cubivue.base.R
import com.cubivue.base.ui.drawer.interfaces.IOnMenuItemClicked
import com.cubivue.base.models.menu.MenuDto
import com.cubivue.base.ui.drawer.fragments.AbstractDrawerFragment
import kotlinx.android.synthetic.main.item_menu.view.*

class MenuAdapter(val mContext: Context, val baseDrawerFragment: AbstractDrawerFragment, val layout: Int) : RecyclerView.Adapter<ViewHolder>() {

    private val items: MutableList<MenuDto> = arrayListOf()
    private var listener: IOnMenuItemClicked? = null

    init {
        this.listener = baseDrawerFragment
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvMenuName?.text = items.get(position).name
        holder.ivMenuImage?.setImageResource(items.get(position).imageRes)

        //check to see if menu is enabled
        if (items.get(position).isActive) {
            holder.rlMenuItem?.setBackgroundResource(R.drawable.bg_menu_item_enabled)
            holder.itemView.isEnabled = true
        } else {
            holder.rlMenuItem?.setBackgroundResource(R.drawable.bg_menu_item_disabled)
            holder.itemView.isEnabled = false
        }

        //check to see if counter is enabled
        if (items.get(position).isCounterVisible) {
            holder.tvCounter?.visibility = View.VISIBLE
            holder.tvCounter?.text = items.get(position).counterValue.toString()
        } else {
            holder.tvCounter?.visibility = View.GONE
        }

        //listeners
        holder.itemView.setOnClickListener({
            Log.i("Item Clicked", "$position")
            listener?.onItemClick(items, position)
        })
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    fun addMenu(menu: MenuDto) {
        items.add(menu)
        notifyItemInserted(items.size - 1)
    }

    fun updateMenu(menu: MenuDto) {

        for (position in items.indices) {
            val menuItem = items.get(position)
            if (menuItem.id.equals(menu.id)) {

                menuItem.name = menu.name
                menuItem.imageRes = menu.imageRes
                menuItem.isActive = menu.isActive
                menuItem.isCounterVisible = menu.isCounterVisible
                menuItem.counterValue = menu.counterValue
                notifyItemChanged(position)
                return
            }
        }
    }

    fun updateMenu(menu: MenuDto, position: Int) {
        val menuItem = items.get(position)

        if (menuItem.id.equals(menu.id)) {
            menuItem.name = menu.name
            menuItem.imageRes = menu.imageRes
            menuItem.isActive = menu.isActive
            menuItem.isCounterVisible = menu.isCounterVisible
            menuItem.counterValue = menu.counterValue
            notifyItemChanged(position)
        }
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvMenuName = view.tvMenuName
    val tvCounter = view.tvCounter
    val ivMenuImage = view.ivMenuImage
    val rlMenuItem = view.rlMenuItem
}