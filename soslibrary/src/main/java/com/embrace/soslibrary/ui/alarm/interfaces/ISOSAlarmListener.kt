package com.embrace.soslibrary.ui.alarm.interfaces

import java.io.Serializable

interface ISOSAlarmListener : Serializable {
    fun onAlarmSent()
    fun onAlarmDismissed()
}
