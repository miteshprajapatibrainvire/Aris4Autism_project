package com.example.aris4autism_project.model.subscriptionmodel


import com.google.gson.annotations.SerializedName

data class SubscriptionModel(

    @SerializedName("original")
    val original: SubscriptionOriginal

//    @SerializedName("data")
//    val `data`: Data,
//    @SerializedName("meta")
//    val meta: Meta
)