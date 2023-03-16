package com.example.aris4autism_project.model.howitworkmodel


import com.google.gson.annotations.SerializedName

data class YoutubeVideoResponse(
//    @SerializedName("data")
//    val `data`: DataXXXXXXXXXXXXXXXXXXXXXXXX,
//    @SerializedName("meta")
//    val meta: MetaXXXXXXXXXXXXXXXXXXX

    @SerializedName("learners")
    val learners: ArrayList<VideoLearners>,
    @SerializedName("overview")
    val overview: ArrayList<VideoOverview>,
    @SerializedName("subusers")
    val subusers: ArrayList<VideoSubuser>
)
{
    override fun toString(): String {
        return "YoutubeVideoResponse(learners=$learners, overview=$overview, subusers=$subusers)"
    }
}
