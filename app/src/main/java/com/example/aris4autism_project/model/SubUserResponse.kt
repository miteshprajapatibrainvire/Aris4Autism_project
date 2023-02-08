package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class SubUserResponse(
    @SerializedName("data")
    val `data`: DataXXXXXX,
    @SerializedName("meta")
    val meta: MetaXXXXX
)
{
    override fun toString(): String {
        return "SubUserResponse(`data`=$`data`, meta=$meta)"
    }
}