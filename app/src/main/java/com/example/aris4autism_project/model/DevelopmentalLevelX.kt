package com.example.aris4autism_project.model

data class DevelopmentalLevelX(
    val answer: String,
    val answered_label_id: String,
    val answered_on: String,
    val development_question_id: Int,
    val identifiers: String,
    val label_0: String,
    val label_1: String,
    val label_id_0: Int,
    val label_id_1: Int,
    val logic: List<LogicX>,
    val question: String,
    val question_uuid: String
)