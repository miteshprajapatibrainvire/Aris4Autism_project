package com.example.aris4autism_project.model.learnermodel


import com.google.gson.annotations.SerializedName

data class LearnerOriginal(
    @SerializedName("data")
    val `data`: ArrayList<LearnerData>,
      @SerializedName("recordsFiltered")
    val recordsFiltered: Int,
    @SerializedName("recordsTotal")
    val recordsTotal: Int
)