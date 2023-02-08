package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class MetaXXXXX(
    @SerializedName("message")
    val message: String,
    @SerializedName("message_code")
    val messageCode: String,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("status_code")
    val statusCode: Int
)
{
    override fun toString(): String {
        return "MetaXXXXX(message='$message', messageCode='$messageCode', status=$status, statusCode=$statusCode)"
    }
}