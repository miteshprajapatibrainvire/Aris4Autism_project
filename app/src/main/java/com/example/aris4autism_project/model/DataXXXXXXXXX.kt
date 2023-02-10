package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class DataXXXXXXXXX(
    @SerializedName("age")
    val age: Int,
    @SerializedName("assessment")
    val assessment: Assessment,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("get_learner_icon")
    val getLearnerIcon: GetLearnerIconX,
    @SerializedName("icon_id")
    val iconId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("srNum")
    val srNum: Int,
    @SerializedName("sub_user_ids")
    val subUserIds: List<SubUserId>,
    @SerializedName("subscription_id")
    val subscriptionId: Int,
    @SerializedName("user_subscriptions")
    val userSubscriptions: UserSubscriptionsX,
    @SerializedName("uuid")
    val uuid: String
)