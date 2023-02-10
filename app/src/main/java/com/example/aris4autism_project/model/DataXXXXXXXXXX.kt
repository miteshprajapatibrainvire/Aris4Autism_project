package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class DataXXXXXXXXXX(
    @SerializedName("exception")
    val exception: Any,
    @SerializedName("headers")
    val headers: HeadersXXX,
    @SerializedName("original")
    val original: OriginalXXX
)