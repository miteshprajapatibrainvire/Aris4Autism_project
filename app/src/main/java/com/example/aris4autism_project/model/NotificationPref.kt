package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class NotificationPref(
    @SerializedName("learner_id")
    val learnerId: Int,
    @SerializedName("notification_on")
    val notificationOn: Int
)