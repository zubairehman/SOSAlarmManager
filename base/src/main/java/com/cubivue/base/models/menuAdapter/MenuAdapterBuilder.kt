package com.cubivue.base.models.menuAdapter

import android.support.annotation.LayoutRes

class MenuAdapterBuilder {
    @LayoutRes
    private var layout: Int = 0
    private var columnCount: Int = 0

    fun setLayout(layout: Int): MenuAdapterBuilder {
        this.layout = layout
        return this
    }

    fun setColumnCount(columnCount: Int): MenuAdapterBuilder {
        this.columnCount = columnCount
        return this
    }

    fun create(): MenuAdapterDto {
        val menuAdapterDto = MenuAdapterDto(layout, columnCount)

        if (menuAdapterDto.layout == 0) {
            throw IllegalStateException("Item Layout can not be null!")
        }

        if (menuAdapterDto.columnCount == 0) {
            throw IllegalStateException("Column count can not be 0!")
        }
        return menuAdapterDto
    }
}