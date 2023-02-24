package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class LearnerXXXXXX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("page_name")
    val pageName: String,
    @SerializedName("thumbnail_name")
    val thumbnailName: String,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("video_link")
    val videoLink: String,
    @SerializedName("video_link_of")
    val videoLinkOf: String,
    @SerializedName("video_name")
    val videoName: String,
    @SerializedName("video_type")
    val videoType: String,
    @SerializedName("video_url")
    val videoUrl: String
):java.io.Serializable
{
    override fun toString(): String {
        return "LearnerXXXXXX(id=$id, pageName='$pageName', thumbnailName='$thumbnailName', thumbnailUrl='$thumbnailUrl', title='$title', uuid='$uuid', videoLink='$videoLink', videoLinkOf='$videoLinkOf', videoName='$videoName', videoType='$videoType', videoUrl='$videoUrl')"
    }
}
