package com.example.aris4autism_project.model.subuserinnermodel

import com.example.aris4autism_project.model.GetLearnerIconXXXX
import com.example.aris4autism_project.model.UserSubscriptionsXXXX

data class SubUserInnerLearner(
    val get_learner_icon: GetLearnerIconXXXX,
    val icon_id: Int,
    val id: Int,
    val name: String,
    val subscription_id: Int,
    val user_subscriptions: UserSubscriptionsXXXX
)