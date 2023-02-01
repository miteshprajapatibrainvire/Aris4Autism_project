package com.example.aris4autism_project.model

class LearnerModel(var name:String,var gen:String,
var yearDetails:String,var dob:String,var monthPlan:String,var startToEndDob:String)
{
    override fun toString(): String {
        return "LearnerModel(name='$name', gen='$gen', yearDetails='$yearDetails', dob='$dob', monthPlan='$monthPlan', startToEndDob='$startToEndDob')"
    }
}