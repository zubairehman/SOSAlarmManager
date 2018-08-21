package com.cubivue.app.ui.login

import android.arch.lifecycle.ViewModel
import com.cubivue.app.data.repositories.LoginRepository
import com.cubivue.base.models.apis.JobResponse
import io.reactivex.Observable
import javax.inject.Inject

class LoginViewModel @Inject constructor(private var repo: LoginRepository) : ViewModel() {

    fun getAssignedJobs(): Observable<JobResponse> {
        return repo.getAssignedJobs()
    }

}