package com.example.aris4autism_project.model

import com.example.aris4autism_project.Utils.Utils

class SummaryPassModel(
    val userId:String="",
    val idData:Int=0,
    val iconUrl:String="",
    val fullname: String="",
    val subscriptinoId: String="",
    val gen: String="",
    val dob:String="",
    val startDob: String="",
    val endDob: String="",
    val monthlyplan: String=""
):java.io.Serializable {

    override fun toString(): String {
        return "SummaryPassModel(fullname='$fullname', subscriptinoId='$subscriptinoId', gen='$gen', dob='$dob', startDob='$startDob', endDob='$endDob', monthlyplan='$monthlyplan')"
    }

    fun dobFunction():String{
        return "DOB :"+dob
    }

    fun dobToAge():String{
        return Utils.dobToAge(dob)
    }
}