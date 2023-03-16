package com.example.aris4autism_project.model


import com.example.aris4autism_project.model.howitworkmodel.VideoLearners
import com.example.aris4autism_project.model.howitworkmodel.VideoOverview
import com.example.aris4autism_project.model.howitworkmodel.VideoSubuser
import com.google.gson.annotations.SerializedName

data class DataXXXXXXXXXXXXXXXXXXXXXXXX(
    @SerializedName("learners")
    val learners: ArrayList<VideoLearners>,
    @SerializedName("overview")
    val overview: ArrayList<VideoOverview>,
    @SerializedName("subusers")
    val subusers: ArrayList<VideoSubuser>
)
{
    override fun toString(): String {
        return "DataXXXXXXXXXXXXXXXXXXXXXXXX(learners=$learners, overview=$overview, subusers=$subusers)"
    }
}