package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class DataXXXXXXXX(
    @SerializedName("exception")
    val exception: Any,
    @SerializedName("headers")
    val headers: HeadersXX,
    @SerializedName("original")
    val original: OriginalXX
)
{
    override fun toString(): String {
        return "DataXXXXXXXX(exception=$exception, headers=$headers, original=$original)"
    }
}
