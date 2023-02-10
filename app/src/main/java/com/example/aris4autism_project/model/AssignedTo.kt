package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class AssignedTo(
    @SerializedName("age")
    val age: Int,
    @SerializedName("age_unit")
    val ageUnit: String,
    @SerializedName("care_taker_id")
    val careTakerId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("created_by")
    val createdBy: Int,
    @SerializedName("date_of_birth")
    val dateOfBirth: String,
    @SerializedName("deleted_at")
    val deletedAt: Any,
    @SerializedName("deleted_by")
    val deletedBy: Any,
    @SerializedName("extra_note")
    val extraNote: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("icon_id")
    val iconId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("learner_icon")
    val learnerIcon: LearnerIcon,
    @SerializedName("name")
    val name: String,
    @SerializedName("notification_on")
    val notificationOn: Int,
    @SerializedName("subscription_id")
    val subscriptionId: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("updated_by")
    val updatedBy: Int,
    @SerializedName("uuid")
    val uuid: String
)