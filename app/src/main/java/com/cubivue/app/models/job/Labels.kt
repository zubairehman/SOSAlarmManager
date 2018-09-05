package com.cubivue.app.models.job

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class Labels() : RealmObject() {

    var label: String? = null

    constructor(product: String?) : this() {
        this.label = product
    }
}