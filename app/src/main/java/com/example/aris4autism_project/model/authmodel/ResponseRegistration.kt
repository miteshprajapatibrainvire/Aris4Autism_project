package com.example.aris4autism_project.model.authmodel


import com.google.gson.annotations.SerializedName

data class ResponseRegistration(

//    @SerializedName("data")
//    val `data`: DataX,
//    @SerializedName("meta")
//    val meta: MetaX

    @SerializedName("message")
    val message: String,
    @SerializedName("message_code")
    val messageCode: String,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("status_code")
    val statusCode: Int

//    @SerializedName("status")
//    val status: Int,
//    @SerializedName("uuid")
//    val uuid: String
)