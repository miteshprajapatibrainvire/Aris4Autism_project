package com.example.aris4autism_project.model

class SubUserModel(val idImgSubUser:Int,
                   val NameSubUser:String,
                   val EmailSubUser:String,
                   val mobileNo:String,
                   val status:String,
                   val assiLearner:String="Assigned learner",
                   val assignLearnerName: ArrayList<String>,
                   val  assignModel:ArrayList<LearnerModel>
):java.io.Serializable {

    override fun toString(): String {
        return "SubUserModel(idImgSubUser=$idImgSubUser, NameSubUser='$NameSubUser', EmailSubUser='$EmailSubUser', mobileNo='$mobileNo', assiLearner='$assiLearner', assignLearnerName=$assignLearnerName, assignModel=$assignModel)"
    }

    @JvmName("getStatus1")
    fun getStatus():String
    {

        if(status=="1")
        {
            return "Active"
        }
        else
        {
            return "Pending"
        }

    }



}