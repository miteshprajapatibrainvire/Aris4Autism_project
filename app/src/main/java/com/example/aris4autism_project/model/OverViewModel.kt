package com.example.aris4autism_project.model

class OverViewModel(var id:Int,var imgId:Int,var name:String,var gen:String,
                    var yearDetails:String,var dob:String,var monthPlan:String,var startToEndDob:String,var activeStatus:Boolean,var startDob:String,var  endDob:String,var dignosis:ArrayList<DiagnosisModel>,var pastSubscription:ArrayList<PastSubscriptionModel>):java.io.Serializable
{

    override fun toString(): String {
        return "OverViewModel(id=$id, imgId=$imgId, name='$name', gen='$gen', yearDetails='$yearDetails', dob='$dob', monthPlan='$monthPlan', startToEndDob='$startToEndDob', activeStatus=$activeStatus, startDob='$startDob', endDob='$endDob', dignosis=$dignosis)"
    }

}