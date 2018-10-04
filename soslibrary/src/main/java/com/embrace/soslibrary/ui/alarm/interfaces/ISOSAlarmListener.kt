package com.embrace.soslibrary.ui.alarm.interfaces

import java.io.Serializable

interface ISOSAlarmListener {
    fun onAlarmSent()
    fun onAlarmDismissed()
}
