package com.example.aris4autism_project.model


import com.google.gson.annotations.SerializedName

data class QueryX(
    @SerializedName("bindings")
    val bindings: List<Any>,
    @SerializedName("query")
    val query: String,
    @SerializedName("time")
    val time: Double
)
{
    override fun toString(): String {
        return "QueryX(bindings=$bindings, query='$query', time=$time)"
    }
}