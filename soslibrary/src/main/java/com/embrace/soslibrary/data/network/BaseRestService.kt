package com.embrace.soslibrary.data.network

import com.embrace.soslibrary.constants.Constants.Headers.HEADER_AUTHORIZATION
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface BaseRestService {

    @POST(NOTIFICATION + "/Alarm")
    fun sendAlarm(@Header(HEADER_AUTHORIZATION) access: String, @Body requestBody: RequestBody): Observable<String>

    @POST(NOTIFICATION + "/DismissAlarmByApp")
    fun dismissAlarm(@Header(HEADER_AUTHORIZATION) access: String, @Body requestBody: RequestBody): Observable<String>

    companion object {
        const val NOTIFICATION = "api/Notification"
    }
}