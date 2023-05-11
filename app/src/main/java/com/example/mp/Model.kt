package com.example.mp

class USER {

    var names:String = ""
    var email:String = ""
    var age:String = ""
    var time_id:String = ""

    constructor(names: String, email: String, age: String, time_id: String) {
        this.names = names
        this.email = email
        this.age = age
        this.time_id = time_id.toString()
    }
    constructor()
}