package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class SubscriptionListResponse(

//    @SerializedName("longDuration")
//    val longDuration: List<LongDuration>,
//    @SerializedName("max_allowed_subscription")
//    val maxAllowedSubscription: Int,
//    @SerializedName("shortDuration")
//    val shortDuration: ArrayList<ShortDuration>,
//    @SerializedName("thumbnail_url")
//    val thumbnailUrl: String,
//    @SerializedName("unassigened_subscriptions")
//    val unassigenedSubscriptions: Int,
//    @SerializedName("video_url")
//    val videoUrl: String

    @SerializedName("data")
    val `data`: DataXXXXXXXXXXXXXXXXXXXXXXX,
    @SerializedName("meta")
    val meta: MetaXXXXXXXXXXXXXXXXXX
)