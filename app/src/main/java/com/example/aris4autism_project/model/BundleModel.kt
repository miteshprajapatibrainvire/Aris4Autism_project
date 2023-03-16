package com.example.aris4autism_project.model

import com.example.aris4autism_project.model.editlearnermodel.SingleEditUserLearnerDiagnosis

class BundleModel(var uuid:String,
                  val name:String,
                  val gender:String, val dob:String,
                  val monthlyplan:String,
                  val activeStatus:String,
                  val startDob:String,
                  val endDob:String,
                  val diagnotsisArray:ArrayList<SingleEditUserLearnerDiagnosis>,
                  val subscriptionId:String,
                  val iconImg:String):java.io.Serializable
{
    constructor() : this("","","","","","","","",ArrayList<SingleEditUserLearnerDiagnosis>(),"","") {

    }


}