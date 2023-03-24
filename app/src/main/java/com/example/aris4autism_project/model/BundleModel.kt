package com.example.aris4autism_project.model

import com.example.aris4autism_project.model.learnermodel.LearnerDiagnosisData

class BundleModel(var imgId:String?,
                  var uuid:String,
                  val iconImg:String,
                    val subscriptionId:String):java.io.Serializable
{
    constructor() : this("","","","") {

    }


}