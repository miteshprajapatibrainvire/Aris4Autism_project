package com.example.aris4autism_project.model


import com.example.aris4autism_project.model.userprofilemodel.ProfileHeaders
import com.example.aris4autism_project.model.userprofilemodel.ProfileOriginal
import com.google.gson.annotations.SerializedName

data class DataXXXXXXXXXXXXXXXXXXXX(
    @SerializedName("exception")
    val exception: Any,
    @SerializedName("headers")
    val headers: ProfileHeaders,
    @SerializedName("original")
    val original: ProfileOriginal
)