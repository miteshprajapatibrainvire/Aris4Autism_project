package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class UserOrderSubscriptionXX(
    @SerializedName("autorenew_info")
    val autorenewInfo: Any,
    @SerializedName("id")
    val id: Int
)