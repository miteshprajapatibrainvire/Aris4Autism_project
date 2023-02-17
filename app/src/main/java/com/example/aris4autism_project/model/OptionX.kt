package com.example.aris4autism_project.model

data class OptionX(
    val document_id: Int,
    val option_type: String,
    val option_value: String,
    val option_value_id: String,
    val selected: Boolean,
    val selected_on: String
)