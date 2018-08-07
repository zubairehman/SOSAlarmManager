package com.cubivue.app.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Dog() : RealmObject() {

    @PrimaryKey
    var dogId: String = ""
    var name: String = ""

    constructor(name: String, dogId: String) : this() {
        this.name = name
        this.dogId = dogId
    }
}