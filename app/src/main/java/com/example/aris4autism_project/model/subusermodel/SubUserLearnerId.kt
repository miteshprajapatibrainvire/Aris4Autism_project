package com.example.aris4autism_project.model.subusermodel


import com.google.gson.annotations.SerializedName

data class SubUserLearnerId(
    @SerializedName("id")
    val id: Int,
    @SerializedName("learner")
    val learner: SubUserLearner,
    @SerializedName("learner_id")
    val learnerId: Int,
    @SerializedName("sub_user_id")
    val subUserId: Int
)