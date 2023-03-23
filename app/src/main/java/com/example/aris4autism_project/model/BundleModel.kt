package com.example.aris4autism_project.model

import com.example.aris4autism_project.model.learnermodel.LearnerDiagnosisData

class BundleModel(var imgId:String?,
                  var uuid:String,
                  val name:String,
                  val gender:String, val dob:String,
                  val monthlyplan:String,
                  val activeStatus:String,
                  val startDob:String,
                  val endDob:String,
                  val diagnotsisArray:ArrayList<LearnerDiagnosisData>,
                  val subscriptionId:String,
                  val iconImg:String):java.io.Serializable
{
    constructor() : this("","","","","","","","","",ArrayList<LearnerDiagnosisData>(),"","") {

    }


}