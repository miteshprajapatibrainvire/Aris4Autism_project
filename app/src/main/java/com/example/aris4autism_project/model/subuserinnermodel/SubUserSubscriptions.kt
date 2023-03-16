package com.example.aris4autism_project.model.subuserinnermodel

data class SubUserSubscriptions(
    val currency: String,
    val currency_symbol: String,
    val duration: Int,
    val end_date: String,
    val grandtotal: String,
    val id: Int,
    val start_date: String,
    val status: String,
    val subscription_order_id: Int,
    val title: String
)