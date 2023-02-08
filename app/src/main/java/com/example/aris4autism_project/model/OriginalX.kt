package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class OriginalX(
    @SerializedName("data")
    val `data`: List<DataXXXXXXX>,
    @SerializedName("draw")
    val draw: Int,
    @SerializedName("input")
    val input: InputX,
    @SerializedName("queries")
    val queries: List<QueryX>,
    @SerializedName("recordsFiltered")
    val recordsFiltered: Int,
    @SerializedName("recordsTotal")
    val recordsTotal: Int
)
{
    override fun toString(): String {
        return "OriginalX(`data`=$`data`, draw=$draw, input=$input, queries=$queries, recordsFiltered=$recordsFiltered, recordsTotal=$recordsTotal)"
    }
}
