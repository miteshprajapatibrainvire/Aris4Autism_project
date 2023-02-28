package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class DiagnosisDetailResponse(
    @SerializedName("data")
    val `data`: ArrayList<DataXXXXXXXXXXXXXXXXXXXXXXXXX>,
    @SerializedName("meta")
    val meta: MetaXXXXXXXXXXXXXXXXXXXX
)
{
    override fun toString(): String {
        return "DiagnosisDetailResponse(`data`=$`data`, meta=$meta)"
    }
}
