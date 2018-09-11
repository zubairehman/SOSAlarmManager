package com.cubivue.app.models.job

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Job() : RealmObject() {

    @PrimaryKey
    var jobId: String = ""
    var jobName: String = ""
    var productionDate: String = ""

    var taskList: RealmList<Task>? = null
    var productsList: RealmList<Products>? = null
    var addressedProductsList: RealmList<AddressedProducts>? = null
    var labelsList: RealmList<Labels>? = null
    var keysList: RealmList<Keys>? = null

    constructor(id: String) : this() {
        this.jobId = id
    }
}