package com.example.aris4autism_project.model.overviewinnerdetail

data class OverviewinnerTopic(
    val questions: List<OverviewinnerQuestion>,
    val title: String,
    val topic_id: Int,
    val topic_slug: String
)