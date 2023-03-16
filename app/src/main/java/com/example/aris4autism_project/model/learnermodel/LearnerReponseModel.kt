package com.example.aris4autism_project.model.learnermodel


import com.google.gson.annotations.SerializedName

data class LearnerReponseModel(

    @SerializedName("original")
    val original: LearnerOriginal
)