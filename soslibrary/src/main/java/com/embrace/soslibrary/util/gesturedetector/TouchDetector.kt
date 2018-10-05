package com.embrace.soslibrary.util.gesturedetector

import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.embrace.soslibrary.ui.alarm.interfaces.IOnTouchListener
import java.io.Serializable

class TouchDetector(context: IOnTouchListener) : Serializable {

    private var listener: IOnTouchListener? = null
    private var touchSequenceCount = 0
    private var handlerTouchSequenceDetection: Handler? = null
    private var runnableTouchSequenceDetection: Runnable? = null

    init {
        this.listener = context
    }

    fun setupTouchSequenceDetection(view: View) {

        view.setOnTouchListener { v, event ->

            val action = event.action
            if (action == MotionEvent.ACTION_DOWN) {

                Log.d("setupTouchDetection", "touchCount: " + (touchSequenceCount + 1));

                if (touchSequenceCount == 0) {
                    handlerTouchSequenceDetection?.postDelayed(runnableTouchSequenceDetection, 1000);
                } else {
                    if (touchSequenceCount == 3) {
                        //Do something here
                        this.listener?.onTouch()
                        Log.i("Touch", "Do Something here and the touchCount is: $touchSequenceCount")
                        resetTouchSequenceDetection(false)
                    }
                }

                touchSequenceCount += 1;
            }

            return@setOnTouchListener false
        }

        handlerTouchSequenceDetection = Handler();

        runnableTouchSequenceDetection = Runnable() {
            run() {
                Log.d("setupTouchDetection", "reset touchCount");
                resetTouchSequenceDetection(false);
            }
        };

    }

    private fun resetTouchSequenceDetection(removeRunnable: Boolean) {

        try {
            if (removeRunnable) {
                handlerTouchSequenceDetection?.removeCallbacks(runnableTouchSequenceDetection);
            }

            touchSequenceCount = 0;
            Log.i("Touch", "touchCount: $touchSequenceCount")
        } catch (ex: Exception) {

        }
    }
}