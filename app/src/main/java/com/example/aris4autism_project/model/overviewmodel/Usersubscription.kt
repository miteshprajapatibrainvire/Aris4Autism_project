package com.example.aris4autism_project.model.overviewmodel


import com.google.gson.annotations.SerializedName

data class Usersubscription(
    @SerializedName("currency_symbol")
    val currencySymbol: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("subscription_order_detail")
    val subscriptionOrderDetail: SubscriptionOrderDetail,
    @SerializedName("subscription_order_id")
    val subscriptionOrderId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("user_order_subscription")
    val userOrderSubscription: UserOrderSubscription
):java.io.Serializable
{
    fun statusBool():Boolean{
        if(status=="")
        {
            return false
        }
        else {
            return true
        }
    }

    fun statusData():String {
        if(status=="")
        {
            return "Expired"
        }
        else
        {
            return "Active"
        }
    }

    fun fullStartandEnd():String
    {
        if(this.startDate==null && this.endDate==null)
        {
            return "null to null"
        }
        else
        {
            return "${startDate} to ${endDate}"
        }
    }
}

