package com.example.aris4autism_project.model.subscriptionmodel.subscriptionmodelresponse


import com.google.gson.annotations.SerializedName

data class SubscriptionUserOrderSubscription(
    @SerializedName("autorenew_info")
    val autorenewInfo: Any,
    @SerializedName("id")
    val id: Int
)