package com.example.aris4autism_project.model

data class Topic(
    val questions: List<QuestionX>,
    val title: String,
    val topic_id: Int,
    val topic_slug: String
)