package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class RequestLogin(
    @SerializedName("device_token")
    val deviceToken: String,
    @SerializedName("device_type")
    val deviceType: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)
{
    override fun toString(): String {
        return "RequestLogin(deviceToken='$deviceToken', deviceType='$deviceType', email='$email', password='$password')"
    }
}

