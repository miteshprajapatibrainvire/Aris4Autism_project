package com.example.aris4autism_project.model.authmodel


import com.google.gson.annotations.SerializedName

data class RegistrationData(
    @SerializedName("status")
    val status: Int,
    @SerializedName("uuid")
    val uuid: String
)