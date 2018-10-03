package com.embrace.sosalarm.ui.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.embrace.sosalarm.R
import com.embrace.soslibrary.ui.alarm.interfaces.ISOSAlarmListener
import com.embrace.soslibrary.ui.alarm.interfaces.IOnShakeListener
import com.embrace.soslibrary.ui.alarm.interfaces.IOnTouchListener
import com.embrace.soslibrary.ui.alarm.models.SOSAlarmBuilder
import com.embrace.soslibrary.ui.baseclasses.fragment.BaseFragment
import java.util.*
import java.util.concurrent.TimeUnit


class MainFragment : BaseFragment(), ISOSAlarmListener, IOnTouchListener, IOnShakeListener {

    //alarm builder
    var builder: SOSAlarmBuilder? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //constructing builder
        builder = SOSAlarmBuilder().with(this.activity!!)
                .setInitialTimerInMilliSeconds(TimeUnit.SECONDS.toMillis(5)) //Initial timer before alarm gets triggered
                .setRepeatTimerInMilliSeconds(TimeUnit.SECONDS.toMillis(10)) //Time to resend alarm
                .setPin("5555") //User pin (this will help to validate user upon alarm cancellation)
                .setAlarmListener(this) //Callback listener that will notify when alarm is sent/dismissed
                .setTouchListener(this) //Listener to touch events, will send callback on three taps
                .setShakeListener(this) //Listener to shake events, will send callback on device shake

    }

    override fun getExtras(extras: ArrayList<*>) {
    }

    override fun onAlarmDismissed() {
        Log.i("Alarm", "Alarm Dismissed")
    }

    override fun onAlarmSent() {
        Log.i("Alarm", "Alarm Sent")
    }

    override fun onTouch() {
        sendAlarm()
    }

    override fun onShake() {
        sendAlarm()
    }

    fun sendAlarm() {
        builder?.build()
    }
}
