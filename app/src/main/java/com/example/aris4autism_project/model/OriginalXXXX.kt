package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class OriginalXXXX(
    @SerializedName("data")
    val `data`: List<DataXXXXXXXXXXXXX>,
    @SerializedName("draw")
    val draw: Int,
    @SerializedName("input")
    val input: InputXXXX,
    @SerializedName("queries")
    val queries: List<QueryXXXX>,
    @SerializedName("recordsFiltered")
    val recordsFiltered: Int,
    @SerializedName("recordsTotal")
    val recordsTotal: Int
)
{
    override fun toString(): String {
        return "OriginalXXXX(`data`=$`data`, draw=$draw, input=$input, queries=$queries, recordsFiltered=$recordsFiltered, recordsTotal=$recordsTotal)"
    }
}
