package com.example.aris4autism_project.model

class BundleModel(var uuid:String,
                  val name:String,
                  val gender:String, val dob:String,
                  val monthlyplan:String,
                  val activeStatus:String,
                  val startDob:String,
                  val endDob:String,
                  val diagnotsisArray:ArrayList<LearnerDiagnosisData>,
                  val subscriptionId:String,
                  val iconImg:String)
{
    constructor() : this("","","","","","","","",ArrayList<LearnerDiagnosisData>(),"","") {

    }


}