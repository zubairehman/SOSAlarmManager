package com.cubivue.app.models.job

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class Keys() : RealmObject() {

    var key: String? = null

    constructor(product: String?) : this() {
        this.key = product
    }
}