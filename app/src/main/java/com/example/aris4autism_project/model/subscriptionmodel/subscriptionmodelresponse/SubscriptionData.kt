package com.example.aris4autism_project.model.subscriptionmodel.subscriptionmodelresponse


import com.example.aris4autism_project.model.AssignedTo
import com.example.aris4autism_project.model.UserOrderSubscriptionXX
import com.google.gson.annotations.SerializedName

data class SubscriptionData(
    @SerializedName("assigned_to")
    val assignedTo: AssignedTo,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("currency_symbol")
    val currencySymbol: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("grandtotal")
    val grandtotal: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_cancelled")
    val isCancelled: Boolean,
    @SerializedName("learner_id")
    val learnerId: Int,
    @SerializedName("srNum")
    val srNum: Int,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("subscription_order_id")
    val subscriptionOrderId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_order_subscription")
    val userOrderSubscription: UserOrderSubscriptionXX,
    @SerializedName("uuid")
    val uuid: String
)
{
    override fun toString(): String {
        return "DataXXXXXXXXXXXXX(assignedTo=$assignedTo, createdAt='$createdAt', currencySymbol='$currencySymbol', duration=$duration, endDate='$endDate', grandtotal='$grandtotal', id=$id, isCancelled=$isCancelled, learnerId=$learnerId, srNum=$srNum, startDate='$startDate', status='$status', subscriptionOrderId=$subscriptionOrderId, title='$title', updatedAt='$updatedAt', userOrderSubscription=$userOrderSubscription, uuid='$uuid')"
    }


    fun getFullSubscriotionDate():String
    {
        if(startDate.isNotEmpty() && endDate.isNotEmpty())
        {
            return startDate + " to " + endDate
        }
        else
        {
          return  " "
        }
    }
    fun getFullMonthsDetails():String{
        if(title.isNotEmpty())
        {
            return "#"+id.toString()+"-"+title
        }
        else
        {
            return " "
        }
    }
}
