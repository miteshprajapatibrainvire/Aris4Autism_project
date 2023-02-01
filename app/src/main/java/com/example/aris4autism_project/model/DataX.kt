package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("status")
    val status: Int,
    @SerializedName("uuid")
    val uuid: String
)