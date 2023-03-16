package com.example.aris4autism_project.model.learnermodel


import com.example.food_nutrition_recipe_app.model.clonemodel.AddLearnerMeta
import com.example.food_nutrition_recipe_app.model.clonemodel.AddlearnerData
import com.google.gson.annotations.SerializedName

data class AddNewLearnerResponse(
    @SerializedName("data")
    val `data`: AddlearnerData,
    @SerializedName("meta")
    val meta: AddLearnerMeta
)