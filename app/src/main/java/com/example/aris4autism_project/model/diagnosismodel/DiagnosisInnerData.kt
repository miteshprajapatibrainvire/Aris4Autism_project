package com.example.aris4autism_project.model.diagnosismodel


import com.google.gson.annotations.SerializedName

data class DiagnosisInnerData(
    @SerializedName("id")
    val id: Int=0,

    @SerializedName("title")
    val title: String="",
    @SerializedName("slug")
val slug: String=""
)
{
    override fun hashCode(): Int {
        var result=this.id
        var slug=this.slug
        var title=this.title
        return result
    }

    override fun toString(): String {
        return "InnerData(id=$id, slug='$slug', title='$title')"
    }
}