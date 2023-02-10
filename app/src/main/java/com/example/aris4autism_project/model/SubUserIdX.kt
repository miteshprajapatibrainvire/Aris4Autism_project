package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class SubUserIdX(
    @SerializedName("learner_id")
    val learnerId: Int,
    @SerializedName("name")
    val name: String
)