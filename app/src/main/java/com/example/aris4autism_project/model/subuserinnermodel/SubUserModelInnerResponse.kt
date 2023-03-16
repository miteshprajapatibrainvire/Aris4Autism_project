package com.example.aris4autism_project.model.subuserinnermodel

data class SubUserModelInnerResponse(
//    val `data`: DataXXXXXXXXXXXXXXX,
//    val meta: MetaXXXXXXXXXX
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
){
//    override fun toString(): String {
//        return "SubUserModelInnerResponse(`data`=$`data`, meta=$meta)"
//    }
}
