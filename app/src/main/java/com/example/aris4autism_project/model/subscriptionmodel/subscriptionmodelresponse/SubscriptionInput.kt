package com.example.aris4autism_project.model.subscriptionmodel.subscriptionmodelresponse


import com.google.gson.annotations.SerializedName

data class SubscriptionInput(
    @SerializedName("length")
    val length: Any,
    @SerializedName("start")
    val start: Int
)