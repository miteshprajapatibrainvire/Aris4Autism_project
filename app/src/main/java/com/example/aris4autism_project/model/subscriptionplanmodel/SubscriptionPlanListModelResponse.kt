package com.example.aris4autism_project.model.subscriptionplanmodel


import com.google.gson.annotations.SerializedName

data class SubscriptionPlanListModelResponse(

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

    @SerializedName("longDuration")
    val longDuration: List<SubscriptionPlanLongDuration>,
    @SerializedName("max_allowed_subscription")
    val maxAllowedSubscription: Int,
    @SerializedName("shortDuration")
    val shortDuration: ArrayList<SubscriptionPlanShortDuration>,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("unassigened_subscriptions")
    val unassigenedSubscriptions: Int,
    @SerializedName("video_url")
    val videoUrl: String
//    @SerializedName("data")
//    val `data`: DataXXXXXXXXXXXXXXXXXXXXXXX,
//    @SerializedName("meta")
//    val meta: MetaXXXXXXXXXXXXXXXXXX
)
{
    override fun toString(): String {
        return "SubscriptionListResponse(longDuration=$longDuration, maxAllowedSubscription=$maxAllowedSubscription, shortDuration=$shortDuration, thumbnailUrl='$thumbnailUrl', unassigenedSubscriptions=$unassigenedSubscriptions, videoUrl='$videoUrl')"
    }
}
