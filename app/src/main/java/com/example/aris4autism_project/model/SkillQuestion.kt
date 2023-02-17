package com.example.aris4autism_project.model

data class SkillQuestion(
    val domain_id: Int,
    val domain_name: String,
    val domain_slug: String,
    val domain_status: String,
    val levels: List<Level>,
    val overhead_text: String
)