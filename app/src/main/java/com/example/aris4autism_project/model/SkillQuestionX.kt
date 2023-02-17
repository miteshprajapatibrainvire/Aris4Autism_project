package com.example.aris4autism_project.model

data class SkillQuestionX(
    val domain_id: Int,
    val domain_name: String,
    val domain_slug: String,
    val domain_status: String,
    val levels: List<LevelX>,
    val overhead_text: String
)