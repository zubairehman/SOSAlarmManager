package com.cubivue.app.models.job

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Task() : RealmObject() {

    @PrimaryKey
    var id: String ?= null
    var parentId: String ?= null
    var sortNo: String ?= null

    constructor(id: String) : this() {
        this.id = id
    }
}