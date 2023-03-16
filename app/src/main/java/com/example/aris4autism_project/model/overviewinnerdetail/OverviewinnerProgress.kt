package com.example.aris4autism_project.model.overviewinnerdetail

import com.example.food_nutrition_recipe_app.model.clonemodel.OverviewinnerBehavior
import com.example.food_nutrition_recipe_app.model.clonemodel.OverviewinnerDevelopment
import com.example.food_nutrition_recipe_app.model.clonemodel.OverviewinnerSkill

data class OverviewinnerProgress(
    val behavior: OverviewinnerBehavior,
    val development: OverviewinnerDevelopment,
    val skill: OverviewinnerSkill
)