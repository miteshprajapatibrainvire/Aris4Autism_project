package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class DataXXXXXX(
    @SerializedName("exception")
    val exception: Any,
    @SerializedName("headers")
    val headers: HeadersX,
    @SerializedName("original")
    val original: OriginalX
)
{
    override fun toString(): String {
        return "DataXXXXXX(exception=$exception, headers=$headers, original=$original)"
    }
}