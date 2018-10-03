package com.embrace.soslibrary.data.repositories

import com.embrace.soslibrary.data.network.BaseRestService
import com.embrace.soslibrary.constants.Constants
import com.embrace.soslibrary.ui.alarm.models.SOSAlarmDto
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.RequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlarmRepository @Inject constructor(private var baseApi: BaseRestService) : AlarmDataSource {

    val gson = Gson()

    //TODO pass IMEI number in this method and also fix getToken() method
    fun sendAlarmCall(alarmRequest: SOSAlarmDto): Observable<String> {
        val requestBody = RequestBody.create(MediaType.parse("application/json"), gson.toJson(alarmRequest))
        return baseApi.sendAlarm(Constants.Headers.token, requestBody)
    }

    //TODO pass IMEI number in this method and also fix getToken() method
    fun dismissAlarmCall(alarmRequest: SOSAlarmDto): Observable<String> {
        val requestBody = RequestBody.create(MediaType.parse("application/json"), gson.toJson(alarmRequest))
        return baseApi.dismissAlarm(Constants.Headers.token, requestBody)
    }
}