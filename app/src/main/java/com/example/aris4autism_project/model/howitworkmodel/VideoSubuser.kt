package com.example.aris4autism_project.model.howitworkmodel


import com.google.gson.annotations.SerializedName

data class VideoSubuser(
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
    val videoName: Any,
    @SerializedName("video_type")
    val videoType: String,
    @SerializedName("video_url")
    val videoUrl: Any
)