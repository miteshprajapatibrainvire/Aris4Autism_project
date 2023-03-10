package com.example.aris4autism_project.model.overviewmodel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("age")
    val age: Int,
    @SerializedName("assessment")
    val assessment: Any,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String,
    @SerializedName("date_of_birth")
    val dateOfBirth: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("get_learner_icon")
    val getLearnerIcon: GetLearnerIcon,
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
    val userSubscriptions: Any,
    @SerializedName("uuid")
    val uuid: String
)