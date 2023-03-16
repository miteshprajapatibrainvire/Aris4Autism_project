package com.example.aris4autism_project.model.userprofilemodel


import com.google.gson.annotations.SerializedName

data class ProfileIconResponseModel(
//    @SerializedName("data")
//    val `data`: DataXXXXXXXXXXXXXXXXXXXX,
//    @SerializedName("meta")
//    val meta: MetaXXXXXXXXXXXXXXX
    @SerializedName("exception")
    val exception: Any,
    @SerializedName("headers")
    val headers: ProfileHeaders,
    @SerializedName("original")
    val Profileoriginal: ProfileOriginal
)