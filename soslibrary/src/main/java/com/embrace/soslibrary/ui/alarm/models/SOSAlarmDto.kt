package com.embrace.soslibrary.ui.alarm.models

import com.embrace.soslibrary.ui.alarm.interfaces.ISOSAlarmListener

import java.io.Serializable

class SOSAlarmDto(var listener: ISOSAlarmListener?, var isActive: Boolean, var initialTimerInMilliSeconds: Long, var repeatTimeInMilliSeconds: Long, var pin: String?)
