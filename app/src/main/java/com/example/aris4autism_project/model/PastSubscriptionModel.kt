package com.example.aris4autism_project.model

data class PastSubscriptionModel(var id:Int,var monthPlan:String,
var subscriptionId:String,var startDate:String,var endDate:String,var status:Boolean):java.io.Serializable {

    override fun toString(): String {
        return "PastSubscriptionModel(monthPlan='$monthPlan', subscriptionId='$subscriptionId', startDate='$startDate', endDate='$endDate', status=$status)"
    }

}