package com.example.aris4autism_project.model.diagnosismodel


import com.google.gson.annotations.SerializedName

data class DiagnosisDetailResponseModel(
    @SerializedName("id")
    val id: Int=0,
    @SerializedName("title")
    val title: String="",
    @SerializedName("slug")
    val slug: String="",

    var isItemChecked : Boolean = false,
    var isNoItemChecked : Boolean = false,

//    @SerializedName("data")
//    var data: List<InnerData>

//    @SerializedName("meta")
//    val meta: MetaXXXXXXXXXXXXXXXXXXXX
)
{

//    override fun toString(): String {
//        return "DiagnosisDetailResponse(`data`=$`data`, meta=$meta)"
//    }

}
