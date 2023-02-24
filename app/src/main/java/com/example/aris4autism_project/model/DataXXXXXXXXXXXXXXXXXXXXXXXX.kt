package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class DataXXXXXXXXXXXXXXXXXXXXXXXX(
    @SerializedName("learners")
    val learners: ArrayList<LearnerXXXXXX>,
    @SerializedName("overview")
    val overview: ArrayList<Overview>,
    @SerializedName("subusers")
    val subusers: ArrayList<Subuser>
)
{
    override fun toString(): String {
        return "DataXXXXXXXXXXXXXXXXXXXXXXXX(learners=$learners, overview=$overview, subusers=$subusers)"
    }
}