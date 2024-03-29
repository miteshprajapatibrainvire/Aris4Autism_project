package com.example.aris4autism_project.model.subusermodel


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SubUserData(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("created_by")
    val createdBy: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("get_profile_icon")
    val getProfileIcon: SubUserGetProfileIcon,
    @SerializedName("icon_id")
    val iconId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("learner_ids")
    val learnerIds: ArrayList<SubUserLearnerId>,
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
) : Serializable {

    fun getStatusBackground():Boolean{
        if(status.isEmpty())
        {
            return false
        }
        else
        {
            return true
        }
    }

    fun getStatusData():String
    {
        if(status.isEmpty())
        {
            return "Expired"
        }
        else{
            return "Active"
        }
    }

}