package com.embrace.soslibrary.ui.alarm.models

import com.embrace.soslibrary.ui.alarm.interfaces.ISOSAlarmListener

import java.io.Serializable

class SOSAlarmDto(var listener: ISOSAlarmListener?, var deviceId: Int, var lat: Double, var lon: Double, var isActive: Boolean, var initialTimerInMilliSeconds: Long, var repeatTimeInMilliSeconds: Long, var alarmId: String?, var url: String?, var pin: String?) : Serializable
