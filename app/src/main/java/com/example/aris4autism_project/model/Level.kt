package com.example.aris4autism_project.model

data class Level(
    val level_id: Int,
    val level_slug: String,
    val level_status: String,
    val level_title: String,
    val topics: List<Topic>
)