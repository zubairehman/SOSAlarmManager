package com.cubivue.base.models.job

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class JobBase() : RealmObject() {

    @PrimaryKey
    var id: String = ""
    var name: String = ""

    constructor(name: String, id: String) : this() {
        this.name = name
        this.id = id
    }
}