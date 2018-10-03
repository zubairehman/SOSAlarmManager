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
    @Transient
    private var context: Context? = null
    private var listener: ISOSAlarmListener? = null
    private var deviceId: Int = 0
    private var lat: Double = 0.toDouble()
    private var lon: Double = 0.toDouble()
    private var isActive: Boolean = false
    private var initialTimerInMilliSeconds: Long = 0
    private var repeatTimeInMilliSeconds: Long = 0
    private var alarmId: String? = null
    private var url: String? = null
    private var pin: String? = null

    fun with(context: Context): SOSAlarmBuilder {
        this.context = context
        return this
    }

    fun setDeviceId(deviceId: Int): SOSAlarmBuilder {
        this.deviceId = deviceId
        return this
    }

    fun setLat(lat: Double): SOSAlarmBuilder {
        this.lat = lat
        return this
    }

    fun setLon(lon: Double): SOSAlarmBuilder {
        this.lon = lon
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

    fun setAlarmId(alarmId: String): SOSAlarmBuilder {
        this.alarmId = alarmId
        return this
    }

    fun setUrl(url: String): SOSAlarmBuilder {
        this.url = url
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

    fun build(): SOSAlarmBuilder {
        val intent = Intent(context, AlarmActivity::class.java)
        intent.putExtra("dto", SOSAlarmDto(listener, deviceId, lat, lon, isActive,
                initialTimerInMilliSeconds, repeatTimeInMilliSeconds, alarmId, url, pin))
        context!!.startActivity(intent)

        return this
    }
}