package com.example.aris4autism_project.model.subscriptionmodel


import com.google.gson.annotations.SerializedName

data class SubscriptionMeta(
    @SerializedName("message")
    val message: String,
    @SerializedName("message_code")
    val messageCode: String,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("status_code")
    val statusCode: Int
)