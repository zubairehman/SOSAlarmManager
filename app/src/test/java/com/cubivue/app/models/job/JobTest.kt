package com.cubivue.app.models.job

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class JobTest() : RealmObject() {

    @PrimaryKey
    var jobId: String = ""

    constructor(id: String) : this() {
        this.jobId = id
    }
}