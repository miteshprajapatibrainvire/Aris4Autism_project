package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class UserSubscriptionsXXXXXXXXXX(
    @SerializedName("currency")
    val currency: String,
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
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("subscription_order_id")
    val subscriptionOrderId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("user_order_subscription")
    val userOrderSubscription: UserOrderSubscriptionXXXXX
)