package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class UserSubscriptionsXX(
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
    @SerializedName("subscription_order_id")
    val subscriptionOrderId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("user_order_subscription")
    val userOrderSubscription: UserOrderSubscriptionX
)
{

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

    fun fullDate():String
    {
        return startDate+" to "+endDate
    }
}