package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class UserSubscriptions(
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
    @SerializedName("title")
    val title: String
)