package com.cubivue.base.models.menu

class MenuBuilder {
    private var name: String? = null
    private var imageRes = 0
    private var isActive = false
    private var isCounterVisible = false
    private var counterValue = 0

    fun setName(name: String): MenuBuilder {
        this.name = name
        return this
    }

    fun setImageRes(imageRes: Int): MenuBuilder {
        this.imageRes = imageRes
        return this
    }

    fun setIsActive(isActive: Boolean): MenuBuilder {
        this.isActive = isActive
        return this
    }

    fun setIsCounterVisible(isCounterVisible: Boolean): MenuBuilder {
        this.isCounterVisible = isCounterVisible
        return this
    }

    fun setCounterValue(counterValue: Int): MenuBuilder {
        this.counterValue = counterValue
        return this
    }

    fun create(): MenuDto {
        val menuDto = MenuDto(name, imageRes, isActive, isCounterVisible, counterValue)

        if (menuDto.name == null || menuDto.name!!.isEmpty()) {
            throw IllegalStateException("Menu name can not be empty!")
        }

        if (menuDto.imageRes == 0) {
            throw IllegalStateException("Menu Image can not be empty!")
        }
        return menuDto
    }
}