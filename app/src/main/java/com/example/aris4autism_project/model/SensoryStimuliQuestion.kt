package com.example.aris4autism_project.model

data class SensoryStimuliQuestion(
    val identifier: String,
    val options: List<OptionX>,
    val question: String,
    val question_id: Int,
    val question_uuid: String
)