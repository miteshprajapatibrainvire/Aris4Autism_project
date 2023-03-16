package com.example.food_nutrition_recipe_app.model.clonemodel

import com.example.aris4autism_project.model.overviewinnerdetail.OverviewinnerOption

data class OverviewinnerSensoryStimuliQuestion(
    val identifier: String,
    val options: List<OverviewinnerOption>,
    val question: String,
    val question_id: Int,
    val question_uuid: String
)