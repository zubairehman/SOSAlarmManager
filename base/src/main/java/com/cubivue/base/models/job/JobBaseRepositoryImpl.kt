package com.cubivue.base.models.job

import com.cubivue.base.data.local.RealmHelper

class JobBaseRepositoryImpl : JobBaseRepository {

    val helper = RealmHelper()

    override fun createJob(jobBase: JobBase) {
        helper.add(jobBase)
    }

}