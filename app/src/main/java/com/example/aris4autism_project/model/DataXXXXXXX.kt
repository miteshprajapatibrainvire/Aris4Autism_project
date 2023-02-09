package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class DataXXXXXXX(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("created_by")
    val createdBy: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("get_profile_icon")
    val getProfileIcon: GetProfileIconX,
    @SerializedName("icon_id")
    val iconId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("learner_ids")
    val learnerIds: List<LearnerId>,
    @SerializedName("name")
    val name: String,
    @SerializedName("password_link_datetime")
    val passwordLinkDatetime: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("srNum")
    val srNum: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_type")
    val userType: String,
    @SerializedName("uuid")
    val uuid: String
):java.io.Serializable
{
    @JvmName("getStatus1")
    fun getStatus():String{
        if(status.equals("1"))
        {
            return "Active"
        }
        else
        {
            return "Pending"
        }
    }
}