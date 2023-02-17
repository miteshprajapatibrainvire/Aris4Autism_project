package com.example.aris4autism_project.model

data class BehaviorXX(
    val behavior_id: Int,
    val behavior_type: String,
    val display_for_minor: Int,
    val example: String,
    val is_direct_qtn_type: String,
    val overhead_location: String,
    val overhead_text: String,
    val paragraph_location: String,
    val paragraph_text: String,
    val questions: List<QuestionXX>,
    val selected: Boolean,
    val selected_on: String,
    val status: String,
    val title: String,
    val uuid: String
)