package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class OriginalXXXXX(
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
