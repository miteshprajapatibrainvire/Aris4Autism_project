package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("meta")
    val meta: Meta
)
{
    override fun toString(): String {
        return "ResponseLogin(`data`=$`data`, meta=$meta)"
    }
}