package com.cubivue.app.ui.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cubivue.app.R
import com.cubivue.base.modules.baseclasses.fragment.BaseToolbarFragment
import java.util.*

class EmptyFragment : BaseToolbarFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_empty, container, false)
    }

    override fun getExtras(extras: ArrayList<Any>) {

    }

    override fun getTitle(): String? {
        return null
    }
}
