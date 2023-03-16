package com.example.aris4autism_project.model.overviewmodel


import com.google.gson.annotations.SerializedName

data class OverViewSubUserId(
    @SerializedName("learner_id")
    val learnerId: Int,
    @SerializedName("name")
    val name: String
)