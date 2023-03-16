package com.example.aris4autism_project.model.subscriptionmodel


import com.google.gson.annotations.SerializedName

data class Data(
//    @SerializedName("exception")
//    val exception: Any,
//    @SerializedName("headers")
//    val headers: Headers,
    @SerializedName("original")
    val original: SubscriptionOriginal
)