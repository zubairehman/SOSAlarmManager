package com.embrace.soslibrary.ui.alarm.interfaces

import java.io.Serializable

interface IOnShakeListener : Serializable {
    fun onShake()
}