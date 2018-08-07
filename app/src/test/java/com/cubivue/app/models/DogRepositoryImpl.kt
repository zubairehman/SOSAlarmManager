package com.cubivue.app.models

import io.realm.Realm

class DogRepositoryImpl : DogRepository {
    override fun createDog(name: String) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { r ->
            val dog = r.createObject(Dog::class.java)
            dog.name = name
        }
        realm.close()
    }
}