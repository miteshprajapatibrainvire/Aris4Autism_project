package com.example.aris4autism_project.model.overviewmodel


import com.google.gson.annotations.SerializedName

data class UserOrderSubscription(
    @SerializedName("autorenew_info")
    val autorenewInfo: String,
    @SerializedName("id")
    val id: Int
)