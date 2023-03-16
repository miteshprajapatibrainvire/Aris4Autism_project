package com.example.aris4autism_project.model.overviewinnerdetail

import com.example.food_nutrition_recipe_app.model.clonemodel.OverviewInnerDetailGetLearnerIcon
import com.example.food_nutrition_recipe_app.model.clonemodel.OverviewinnerdetailUserSubscriptions

data class OverviewInnerdetailLearner(
    val date_of_birth: String,
    val get_learner_icon: OverviewInnerDetailGetLearnerIcon,
    val icon_id: Int,
    val id: Int,
    val name: String,
    val subscription_id: Int,
    val user_subscriptions: OverviewinnerdetailUserSubscriptions,
    val uuid: String
)