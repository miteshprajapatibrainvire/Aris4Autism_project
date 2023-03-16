package com.example.aris4autism_project.model.subuserinnermodel

data class SubUserInnerLearner(
    val get_learner_icon: SubUserGetLearnerIcon,
    val icon_id: Int,
    val id: Int,
    val name: String,
    val subscription_id: Int,
    val user_subscriptions: SubUserSubscriptions
)