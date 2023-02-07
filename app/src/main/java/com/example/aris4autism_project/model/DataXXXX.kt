package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class DataXXXX(
    @SerializedName("exception")
    val exception: Any,
    @SerializedName("headers")
    val headers: Headers,
    @SerializedName("original")
    val original: Original
)