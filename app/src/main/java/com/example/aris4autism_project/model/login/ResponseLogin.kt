package com.example.aris4autism_project.model.login


import com.example.aris4autism_project.model.login.UserDetail
import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("is_subscription_purchased")
    val isSubscriptionPurchased: Boolean,
    @SerializedName("max_allowed_learner")
    val maxAllowedLearner: String,
    @SerializedName("max_allowed_subscription")
    val maxAllowedSubscription: String,
    @SerializedName("max_allowed_subuser")
    val maxAllowedSubuser: String,
    @SerializedName("redirect_url")
    val redirectUrl: String,
    @SerializedName("regular_skill_trails")
    val regularSkillTrails: Int,
    @SerializedName("userDetail")
    val userDetail: UserDetail
)
{


}