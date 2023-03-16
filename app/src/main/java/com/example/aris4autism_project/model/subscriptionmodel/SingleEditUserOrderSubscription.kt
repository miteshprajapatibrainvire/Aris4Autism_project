package com.example.aris4autism_project.model.subscriptionmodel


import com.google.gson.annotations.SerializedName

data class SingleEditUserOrderSubscription(
    @SerializedName("autorenew_info")
    val autorenewInfo: Any,
    @SerializedName("id")
    val id: Int
)