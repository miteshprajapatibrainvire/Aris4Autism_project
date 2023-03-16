package com.example.aris4autism_project.model.subscriptionmodel.subscriptionmodelresponse


import com.google.gson.annotations.SerializedName

data class SubScriptionResponseModel(
//    @SerializedName("data")
//    val `data`: DataXXXXXXXXXXXX,
//    @SerializedName("meta")
//    val meta: MetaXXXXXXXX
    @SerializedName("original")
    val original: SubscriptionOriginal
)