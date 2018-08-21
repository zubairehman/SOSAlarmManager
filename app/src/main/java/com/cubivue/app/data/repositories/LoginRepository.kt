package com.cubivue.app.data.repositories

import com.cubivue.app.data.network.RestService
import com.cubivue.base.data.network.BaseRestService
import com.cubivue.base.models.apis.JobResponse
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(private var clientApi: RestService, private var baseApi: BaseRestService) : LoginDataSource {

    fun getAssignedJobs(): Observable<JobResponse> {
        return baseApi.getJobsFromServer("1")
    }
}