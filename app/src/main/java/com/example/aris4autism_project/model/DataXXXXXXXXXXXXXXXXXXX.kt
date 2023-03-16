package com.example.aris4autism_project.model


import com.example.aris4autism_project.model.editsubuserdetailmodel.EditSubUserDetailGetProfileIcon
import com.example.aris4autism_project.model.editsubuserdetailmodel.EditSubUserDetailLearnerId
import com.google.gson.annotations.SerializedName

data class DataXXXXXXXXXXXXXXXXXXX(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("get_profile_icon")
    val getProfileIcon: EditSubUserDetailGetProfileIcon,
    @SerializedName("icon_id")
    val iconId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("learner_ids")
    val learnerIds: ArrayList<EditSubUserDetailLearnerId>,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("uuid")
    val uuid: String
)
{
    override fun toString(): String {
        return "DataXXXXXXXXXXXXXXXXXXX(createdAt='$createdAt', email='$email', getProfileIcon=$getProfileIcon, iconId=$iconId, id=$id, learnerIds=$learnerIds, name='$name', phoneNumber='$phoneNumber', status='$status', updatedAt='$updatedAt', uuid='$uuid')"
    }
}