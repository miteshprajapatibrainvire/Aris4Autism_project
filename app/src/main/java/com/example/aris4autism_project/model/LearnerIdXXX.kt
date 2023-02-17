package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class LearnerIdXXX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("learner")
    val learner: LearnerXXXXX,
    @SerializedName("learner_id")
    val learnerId: Int,
    @SerializedName("sub_user_id")
    val subUserId: Int
)