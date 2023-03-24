package com.example.aris4autism_project.model.learnermodel

class BasicDetailBindModel(var fullname:String,
var gender:String,
var dob:String,
var subscription:String) {
    constructor() : this("", "", "", "") {

    }

    @JvmName("setFullname1")
    fun setFullname(fname: String) {
        this.fullname = fname
    }

    @JvmName("setGender1")
    fun setGender(gen: String) {
        this.gender = gen
    }

    @JvmName("setDob1")
    fun setDob(dobDate:String)
    {
        this.dob = dobDate
    }

    @JvmName("setSubscription1")
    fun setSubscription(sub: String)
    {
        this.subscription=sub
    }


}