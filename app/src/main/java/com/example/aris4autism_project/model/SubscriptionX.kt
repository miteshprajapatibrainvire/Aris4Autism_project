package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class SubscriptionX(
    @SerializedName("currency_symbol")
    val currencySymbol: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("status")
    val status: String
)