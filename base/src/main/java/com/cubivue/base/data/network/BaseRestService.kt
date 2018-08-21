package com.cubivue.base.data.network

import com.cubivue.base.models.apis.JobResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface BaseRestService {

    @GET("api/Job/GetMyJobs")
    fun getJobsFromServer(@Query("user_id") userId: String): Observable<JobResponse>
}