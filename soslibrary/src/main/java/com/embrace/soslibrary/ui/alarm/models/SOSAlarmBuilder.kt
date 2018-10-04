package com.embrace.soslibrary.ui.alarm.models

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.hardware.SensorManager

import com.embrace.soslibrary.ui.alarm.AlarmActivity
import com.embrace.soslibrary.ui.alarm.interfaces.ISOSAlarmListener
import com.embrace.soslibrary.ui.alarm.interfaces.IOnShakeListener
import com.embrace.soslibrary.ui.alarm.interfaces.IOnTouchListener
import com.embrace.soslibrary.util.gesturedetector.ShakeDetector
import com.embrace.soslibrary.util.gesturedetector.TouchDetector

import java.io.Serializable

import android.content.Context.SENSOR_SERVICE

class SOSAlarmBuilder : Serializable {
    private var context: Context? = null
    private var listener: ISOSAlarmListener? = null
    private var isActive: Boolean = false
    private var initialTimerInMilliSeconds: Long = 0
    private var repeatTimeInMilliSeconds: Long = 0
    private var pin: String? = null
    private var sosAlarmDto: SOSAlarmDto? = null

    fun with(context: Context): SOSAlarmBuilder {
        this.context = context
        return this
    }

    fun setIsActive(isActive: Boolean): SOSAlarmBuilder {
        this.isActive = isActive
        return this
    }

    fun setInitialTimerInMilliSeconds(initialTimerInMilliSeconds: Long): SOSAlarmBuilder {
        this.initialTimerInMilliSeconds = initialTimerInMilliSeconds
        return this
    }

    fun setRepeatTimerInMilliSeconds(repeatTimeInMilliSeconds: Long): SOSAlarmBuilder {
        this.repeatTimeInMilliSeconds = repeatTimeInMilliSeconds
        return this
    }

    fun setPin(pin: String): SOSAlarmBuilder {
        this.pin = pin
        return this
    }

    fun setAlarmListener(listener: ISOSAlarmListener): SOSAlarmBuilder {
        this.listener = listener
        return this
    }

    fun setTouchListener(listener: IOnTouchListener): SOSAlarmBuilder {
        TouchDetector(listener).setupTouchSequenceDetection((context as Activity).window.decorView.rootView)
        return this
    }

    fun setShakeListener(listener: IOnShakeListener): SOSAlarmBuilder {
        val sensorManager = (context as Activity).getSystemService(SENSOR_SERVICE) as SensorManager
        val sd = ShakeDetector(listener)
        sd.start(sensorManager)
        return this
    }

    fun getActivity(): Activity {
        return context as Activity;
    }

    fun clean() {
        context = null
    }

    fun getSOSAlarmDto (): SOSAlarmDto? {
        return sosAlarmDto;
    }

    fun build(): SOSAlarmBuilder {
        sosAlarmDto = SOSAlarmDto(listener, isActive, initialTimerInMilliSeconds, repeatTimeInMilliSeconds, pin)
        val sosAlarmHelper = SOSAlarmHelper(this)
        sosAlarmHelper.setSOSAlarmListener(listener)
        sosAlarmHelper.startAlarmActivity()

        return this
    }
}