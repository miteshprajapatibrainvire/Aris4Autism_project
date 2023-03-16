package com.example.aris4autism_project.model.editsubuserdetailmodel


import com.google.gson.annotations.SerializedName

data class EditSubUserDetailLearnerId(
    @SerializedName("id")
    val id: Int,
    @SerializedName("learner")
    val learner: EditSubuserLearner,
    @SerializedName("learner_id")
    val learnerId: Int,
    @SerializedName("sub_user_id")
    val subUserId: Int
)