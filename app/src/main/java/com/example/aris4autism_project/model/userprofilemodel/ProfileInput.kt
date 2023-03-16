package com.example.aris4autism_project.model.userprofilemodel


import com.google.gson.annotations.SerializedName

data class ProfileInput(
    @SerializedName("length")
    val length: Any,
    @SerializedName("start")
    val start: Int,
    @SerializedName("user_type")
    val userType: String
)