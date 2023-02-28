package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class EditLearnerModelResponse(
    @SerializedName("data")
    val `data`: DataXXXXXXXXXXXXXXXXXXXXXX,
    @SerializedName("meta")
    val meta: MetaXXXXXXXXXXXXXXXX
)
{
    override fun toString(): String {
        return "EditLearnerModelResponse(`data`=$`data`, meta=$meta)"
    }
}
