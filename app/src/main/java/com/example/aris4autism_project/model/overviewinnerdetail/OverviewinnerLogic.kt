package com.example.aris4autism_project.model.overviewinnerdetail

data class OverviewinnerLogic(
    val ask_question: String,
    val ask_question_id: String,
    val label_id: Int,
    val move_to: OverviewinnerMoveTo,
    val remove_skill_question_with_tag: RemoveSkillQuestionWithTag
)