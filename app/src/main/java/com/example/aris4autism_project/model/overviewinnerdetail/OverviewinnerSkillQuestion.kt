package com.example.food_nutrition_recipe_app.model.clonemodel

import com.example.aris4autism_project.model.overviewinnerdetail.OverviewinnerLevel

data class OverviewinnerSkillQuestion(
    val domain_id: Int,
    val domain_name: String,
    val domain_slug: String,
    val domain_status: String,
    val levels: List<OverviewinnerLevel>,
    val overhead_text: String
)