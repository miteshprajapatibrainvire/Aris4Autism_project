package com.example.aris4autism_project.model.diagnosismodel


import com.google.gson.annotations.SerializedName

data class DiagnosisInnerData(
    @SerializedName("id")
    val id: Int=0,
    @SerializedName("slug")
    val slug: String="",
    @SerializedName("title")
    val title: String=""
)
{
    override fun toString(): String {
        return "InnerData(id=$id, slug='$slug', title='$title')"
    }
}