package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class DataXXXXXXXXXXXXXXXXXXXXXXXXX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("title")
    val title: String
)
{
    override fun toString(): String {
        return "DataXXXXXXXXXXXXXXXXXXXXXXXXX(id=$id, slug='$slug', title='$title')"
    }
}