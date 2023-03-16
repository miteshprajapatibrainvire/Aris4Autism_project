package com.example.aris4autism_project.model.userprofilemodel


import com.google.gson.annotations.SerializedName

data class ProfileData(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("full_path")
    val fullPath: String,
    @SerializedName("icon_url")
    val iconUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("srNum")
    val srNum: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("isSelected")
    var isSelected:Boolean=false,
    @SerializedName("user_type")
    val userType: String,
    @SerializedName("uuid")
    val uuid: String
)