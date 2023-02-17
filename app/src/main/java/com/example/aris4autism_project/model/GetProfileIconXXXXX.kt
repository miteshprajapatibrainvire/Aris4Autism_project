package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class GetProfileIconXXXXX(
    @SerializedName("full_path")
    val fullPath: String,
    @SerializedName("icon_url")
    val iconUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String
)