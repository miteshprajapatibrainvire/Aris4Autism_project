package com.example.aris4autism_project.model.subscriptionmodel


import com.google.gson.annotations.SerializedName

data class SubscriptionOriginal(
    @SerializedName("data")
    val `data`: ArrayList<SubscriptionDataX>,
    @SerializedName("draw")
    val draw: Int,
    @SerializedName("input")
    val input: SubscriptionInput,
    @SerializedName("queries")
    val queries: List<SubscriptionQuery>,
    @SerializedName("recordsFiltered")
    val recordsFiltered: Int,
    @SerializedName("recordsTotal")
    val recordsTotal: Int
)
{
    override fun toString(): String {
        return "Original(`data`=$`data`, draw=$draw, input=$input, queries=$queries, recordsFiltered=$recordsFiltered, recordsTotal=$recordsTotal)"
    }
}
