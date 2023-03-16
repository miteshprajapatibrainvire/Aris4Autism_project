package com.example.aris4autism_project.model.userprofilemodel


import com.example.aris4autism_project.model.DataXXXXXXXXXXXXXXXXXXXXX
import com.example.aris4autism_project.model.InputXXXXX
import com.example.aris4autism_project.model.QueryXXXXX
import com.google.gson.annotations.SerializedName

data class ProfileOriginal(
    @SerializedName("data")
    val `data`: ArrayList<DataXXXXXXXXXXXXXXXXXXXXX>,
    @SerializedName("draw")
    val draw: Int,
    @SerializedName("input")
    val input: InputXXXXX,
    @SerializedName("queries")
    val queries: List<QueryXXXXX>,
    @SerializedName("recordsFiltered")
    val recordsFiltered: Int,
    @SerializedName("recordsTotal")
    val recordsTotal: Int
)
{
    override fun toString(): String {
        return "OriginalXXXXX(`data`=$`data`, draw=$draw, input=$input, queries=$queries, recordsFiltered=$recordsFiltered, recordsTotal=$recordsTotal)"
    }
}
