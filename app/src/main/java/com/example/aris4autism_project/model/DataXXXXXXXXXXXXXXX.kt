package com.example.aris4autism_project.model

import com.example.aris4autism_project.model.subuserinnermodel.SubUserInnerModelGetProfileIcon
import com.example.aris4autism_project.model.subuserinnermodel.SubUserInnerModelLearnerId

data class DataXXXXXXXXXXXXXXX(
    val created_at: String,
    val email: String,
    val get_profile_icon: SubUserInnerModelGetProfileIcon,
    val icon_id: Int,
    val id: Int,
    val learner_ids: ArrayList<SubUserInnerModelLearnerId>,
    val name: String,
    val phone_number: String,
    val status: String,
    val updated_at: String,
    val uuid: String
)