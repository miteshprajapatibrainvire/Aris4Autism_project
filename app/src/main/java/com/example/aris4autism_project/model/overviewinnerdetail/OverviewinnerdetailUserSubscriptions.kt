package com.example.food_nutrition_recipe_app.model.clonemodel

data class OverviewinnerdetailUserSubscriptions(
    val currency: String,
    val currency_symbol: String,
    val duration: Int,
    val end_date: String,
    val grandtotal: String,
    val id: Int,
    val start_date: String,
    val status: String,
    val subscription_order_id: Int,
    val title: String,
    val user_order_subscription: OverviewinnerdetailUserOrderSubscription
)