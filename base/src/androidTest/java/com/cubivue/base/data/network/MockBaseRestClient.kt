package com.cubivue.base.data.network

import com.cubivue.base.models.apis.JobResponse
import io.reactivex.Observable
import io.reactivex.Scheduler

class MockBaseRestClient(val scheduler: Scheduler, var jobResponse: JobResponse = JobResponse("",""), var error: Throwable? = null) : BaseRestService {

    override fun getJobsFromServer(userId: String): Observable<JobResponse> {
        val response = Observable.just(jobResponse)
        return response.subscribeOn(scheduler).observeOn(scheduler)
    }
}