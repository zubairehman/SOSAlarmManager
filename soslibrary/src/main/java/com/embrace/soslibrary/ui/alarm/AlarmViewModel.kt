package com.embrace.soslibrary.ui.alarm

import android.arch.lifecycle.ViewModel
import com.embrace.soslibrary.data.repositories.AlarmRepository
import com.embrace.soslibrary.ui.alarm.models.SOSAlarmDto
import io.reactivex.Observable
import javax.inject.Inject


class AlarmViewModel @Inject internal constructor(private var repo: AlarmRepository): ViewModel() {

    fun sendAlarm(alarmRequest: SOSAlarmDto): Observable<String> {
        return repo.sendAlarmCall(alarmRequest)
    }

    fun dismissAlarm(alarmRequest: SOSAlarmDto): Observable<String> {
        return repo.dismissAlarmCall(alarmRequest)
    }

}