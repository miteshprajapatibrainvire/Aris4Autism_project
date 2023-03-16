package com.example.aris4autism_project.model.editsubuserdetailmodel


import com.google.gson.annotations.SerializedName

data class EditSubuserLearner(
    @SerializedName("get_learner_icon")
    val getLearnerIcon: EditSubUserGetLearnerIcon,
    @SerializedName("icon_id")
    val iconId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("subscription_id")
    val subscriptionId: Int,
    @SerializedName("user_subscriptions")
    val userSubscriptions: EditSubUserSubscriptions
)