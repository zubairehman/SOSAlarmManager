package com.cubivue.base.models.menu

class MenuDto(name: String?, imageRes: Int, isActive: Boolean, isCounterVisible: Boolean, counterValue: Int) {
    var id: String
    var name: String? = null
    var imageRes: Int = 0
    var isActive: Boolean = false
    var isCounterVisible: Boolean = false
    var counterValue: Int = 0

    init {
        this.id = java.util.UUID.randomUUID().toString()
        this.name = name
        this.imageRes = imageRes
        this.isActive = isActive
        this.isCounterVisible = isCounterVisible
        this.counterValue = counterValue
    }

}
