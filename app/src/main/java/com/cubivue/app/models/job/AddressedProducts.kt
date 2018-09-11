package com.cubivue.app.models.job

import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class AddressedProducts() : RealmObject() {

    var product: String? = null
    var quantity: Int? = null

    constructor(product: String?, quantity: Int?) : this() {
        this.product = product
        this.quantity = quantity
    }
}