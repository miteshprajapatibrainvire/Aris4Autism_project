package com.example.aris4autism_project.model

import android.os.Parcel
import android.os.Parcelable

class LearnerModel(var id:Int,var imgId:Int,var name:String,var gen:String,
var yearDetails:String,var dob:String,var monthPlan:String,var startToEndDob:String,var activeStatus:Boolean,var startDob:String,var  endDob:String,var dignosis:ArrayList<DiagnosisModel>):java.io.Serializable
{

}