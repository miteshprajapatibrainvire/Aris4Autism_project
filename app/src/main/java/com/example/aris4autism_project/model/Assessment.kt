package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class Assessment(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("current_stage")
    val currentStage: String,
    @SerializedName("learner_id")
    val learnerId: Int,
    @SerializedName("mongo_assesment_id")
    val mongoAssesmentId: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("subscription")
    val subscription: Subscription,
    @SerializedName("subscription_id")
    val subscriptionId: Int
)