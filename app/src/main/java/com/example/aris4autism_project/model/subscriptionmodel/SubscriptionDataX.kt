package com.example.aris4autism_project.model.subscriptionmodel


import com.google.gson.annotations.SerializedName

data class SubscriptionDataX(
    @SerializedName("assigned_to")
    val assignedTo: Any,
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
    val learnerId: Any,
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
    val userOrderSubscription: UserOrderSubscription,
    @SerializedName("uuid")
    val uuid: String
)
{
    fun assignLearner():Boolean{
        if(assignedTo!=null)
        {
            return true
        }
        else
        {
            return false
        }
    }
}