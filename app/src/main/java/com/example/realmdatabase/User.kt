package com.example.realmdatabase

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class User: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var score: Double = 0.0
    var isHuman: Boolean = true
    override fun toString(): String {
        return "ID: $_id\n name:'$name'\n score:$score\n isHuman:$isHuman\n"
    }
}