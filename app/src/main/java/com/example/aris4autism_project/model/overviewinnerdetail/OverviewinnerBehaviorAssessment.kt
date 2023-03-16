package com.example.food_nutrition_recipe_app.model.clonemodel

import com.example.aris4autism_project.model.overviewinnerdetail.OverviewinnerConfigurations

data class OverviewinnerBehaviorAssessment(
    val behaviors: List<OverviewinnerBehavior>,
    val configurations: OverviewinnerConfigurations,
    val current_step: String,
    val sensory_issues_questions: List<OverviewinnerSensoryIssuesQuestion>,
    val sensory_stimuli_questions: List<OverviewinnerSensoryStimuliQuestion>
)