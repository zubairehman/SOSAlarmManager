package com.embrace.soslibrary.ui.alarm

import android.annotation.TargetApi
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import com.embrace.soslibrary.R
import com.embrace.soslibrary.constants.Constants
import com.embrace.soslibrary.ui.alarm.models.SOSAlarmDto
import com.embrace.soslibrary.util.Utils
import com.embrace.soslibrary.util.fonts.FontUtils
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_alarm.*
import java.util.*
import javax.inject.Inject

class AlarmActivity : AppCompatActivity() {

    //timer variables
    private var countDownTimer: CountDownTimer? = null
    private var handler = Handler()
    private var isAlarmRunning = false

    //builder
    private var sosAlarmDto: SOSAlarmDto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setting flags
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON)

        supportActionBar?.hide()
        setContentView(R.layout.activity_alarm)

        //binding view model
        AndroidInjection.inject(this)

        //getting intent
        sosAlarmDto = intent?.extras?.getSerializable("dto") as SOSAlarmDto?

        //initializing UI and setting click listeners
        initUI()

        //check to see if alarm is already running
        if (!sosAlarmDto?.isActive!!) {
            startTimer()
        } else {
//            val count = Preferences.getInstance().getInt(Constants.ALARM_RESPONDENTS, 0)
//            txt_count!!.text = count.toString() + ""
            card_timer!!.visibility = View.GONE
            card_alarm!!.visibility = View.VISIBLE
            startAlarm()
        }

        //registering event
//        RxBusBuilder.create(EventType::class.java)
//                .withQueuing(this)
//                .withMode(RxBusMode.Main)
//                .subscribe { eventType ->
//                    if (eventType.event_type == EventType.EVENT_ALARM_RESPONDED) {
//                        val count = Preferences.getInstance().getInt(Constants.ALARM_RESPONDENTS, 0)
//                        txt_count!!.text = count.toString() + ""
//                    }
//                }
    }

    private fun initUI() {

        //Apply Fonts
        txt_message!!.typeface = FontUtils.setTypeface(Constants.Fonts.FONT_ROBOTO_MEDIUM, this)
        txt_timer!!.typeface = FontUtils.setTypeface(Constants.Fonts.FONT_ROBOTO_MEDIUM, this)
        btn_send_alarm!!.typeface = FontUtils.setTypeface(Constants.Fonts.FONT_ROBOTO_CONDENSED_BOLD, this)

        //Setting listeners
        btn_back.setOnClickListener {
            card_alarm!!.visibility = View.VISIBLE
            card_pin_code!!.visibility = View.GONE
        }

        btn_close.setOnClickListener {
            stopTimer()
        }

        btn_send_alarm.setOnClickListener {

            if (countDownTimer != null) {
                countDownTimer!!.cancel()
            }

            val alarmId = UUID.randomUUID().toString()
            card_timer!!.visibility = View.GONE
            card_alarm!!.visibility = View.VISIBLE
            startAlarm()

            //Start Power Button Control Service
            if (checkDrawOverlayPermission()) {
//                startService(Intent(this@AlarmActivity, PowerButtonService::class.java))
            }
        }

        btn_stop.setOnClickListener {
            card_alarm!!.visibility = View.GONE
            card_pin_code!!.visibility = View.VISIBLE
        }

        edit_pin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                val length = p0!!.length
                if (length == 4) {
                    Utils.hideKeyboard(this@AlarmActivity)
                }
            }
        })

        btn_sign_in.setOnClickListener {
            if (!TextUtils.isEmpty(edit_pin!!.text.toString())) {

                val password = sosAlarmDto?.pin
                if (edit_pin!!.text.toString() == password) {
                    Utils.hideKeyboard(this)
                    dismissAlarm()
                    stopAlarm()
//                    stopService(Intent(this, PowerButtonService::class.java))
                    finish()

                } else {
                    txt_pin_error!!.visibility = View.VISIBLE
                    object : CountDownTimer(3000, 1000) {

                        override fun onTick(millisUntilFinished: Long) {
                        }

                        override fun onFinish() {
                            txt_pin_error!!.visibility = View.GONE
                        }
                    }.start()
                }
            } else {
                Toast.makeText(this@AlarmActivity, getString(R.string.txt_no_pin), Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onBackPressed() {
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {

        when (keyCode) {

            277 -> {
//                if (Constants.ALARM_ACTIVE && cancelable) {
//                    Constants.ALARM_ACTIVE = false
//                    Preferences.getInstance().save(Constants.IS_ALARM_ACTIVE, false)
//                    finish()
//                }
                return true
            }

            281 -> {
//                if (Constants.ALARM_ACTIVE && cancelable) {
//                    Constants.ALARM_ACTIVE = false
//                    Preferences.getInstance().save(Constants.IS_ALARM_ACTIVE, false)
//                    finish()
//                }
                return true
            }

            KeyEvent.KEYCODE_POWER -> {
                val closeDialog = Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
                sendBroadcast(closeDialog)
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    //Permission Methods:---------------------------------------------------------------------------
    private fun checkDrawOverlayPermission(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }

        return if (!Settings.canDrawOverlays(this)) {
            /** if not construct intent to request permission  */
            val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + packageName))
            startActivityForResult(intent, REQUEST_CODE)
            false
        } else {
            true
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE) {
            if (Settings.canDrawOverlays(this)) {
//                startService(Intent(this, PowerButtonService::class.java))
            }
        }
    }

    //Timer Methods:--------------------------------------------------------------------------------
    private fun startTimer() {
        countDownTimer = object : CountDownTimer(sosAlarmDto?.initialTimerInMilliSeconds!!, 500) {
            override fun onTick(l: Long) {
                val seconds = (l / 1000).toInt()
                txt_timer!!.text = String.format(getString(R.string.txt_timer), seconds.toString())
            }

            override fun onFinish() {
                if (!isAlarmRunning) {
                    stopTimer()
                }
            }
        }.start()
    }

    private fun stopTimer() {
        if (countDownTimer != null) {
            countDownTimer!!.cancel()
        }
        resetAlarmParameters()
        finish()
    }

    private var runnableCode: Runnable = object : Runnable {
        override fun run() {
//            val isAlarmActive = Preferences.getInstance().getBoolean(SyncStateContract.Constants.IS_ALARM_ACTIVE, false)
            if (isAlarmRunning) {
                try {
                    sendAlarm()
                } catch (e: Exception) {
//                    PLog.logThis(TAG, "Send Alarm", Utils.getInstance().getStackTrace(e), PLog.TYPE_ERROR)
                }

                // Repeat this the same runnable code block again another 10 Seconds
                handler.postDelayed(this, sosAlarmDto?.repeatTimeInMilliSeconds!!)
            } else {
                handler.removeCallbacksAndMessages(null)
            }

        }
    }

    private fun startAlarm() {
        isAlarmRunning = true
        sosAlarmDto?.isActive = true
        handler.post(runnableCode)
    }

    private fun stopAlarm() {
        isAlarmRunning = false
        sosAlarmDto?.isActive = false
        handler.removeCallbacksAndMessages(null)
        resetAlarmParameters()
    }

    fun sendAlarm() {
        sosAlarmDto?.listener?.onAlarmSent()
    }

    private fun dismissAlarm() {
        sosAlarmDto?.listener?.onAlarmDismissed()
    }

    private fun resetAlarmParameters() {
        isAlarmRunning = false
        sosAlarmDto?.isActive = false
    }

    companion object {
        const val REQUEST_CODE = 10101
    }

}
