package com.example.aris4autism_project.model.login


import com.google.gson.annotations.SerializedName

data class UserDetail(
    @SerializedName("email")
    val email: String,
    @SerializedName("get_profile_icon")
    val getProfileIcon: GetLoginProfileIcon,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("user_type")
    val userType: String,
    @SerializedName("uuid")
    val uuid: String
)