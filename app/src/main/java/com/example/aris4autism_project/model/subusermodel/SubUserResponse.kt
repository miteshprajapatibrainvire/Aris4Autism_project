package com.example.aris4autism_project.model.subusermodel


import com.google.gson.annotations.SerializedName

data class SubUserResponse(
    @SerializedName("original")
    val original: SubuserOriginal
)