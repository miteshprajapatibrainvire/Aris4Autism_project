package com.example.food_nutrition_recipe_app.model.clonemodel

import com.example.aris4autism_project.model.overviewinnerdetail.OverviewInnerdetailLearner

data class OverviewinnerAssessment(
    val _id: String,
    val assessment_id: String,
    val behavior_assessment: OverviewinnerBehaviorAssessment,
    val behavior_docs: List<OverviewinnerBehaviorDoc>,
    val behavior_level_status: String,
    val created_at: String,
    val developmental_level_status: String,
    val include_doc_tag: List<Any>,
    val learner: OverviewInnerdetailLearner,
    val miscellaneous_docs: OverviewinnerMiscellaneousDocs,
    val questions: OverviewinnerQuestions,
    val remove_skill_tag: List<String>,
    val replacement_behavior_documents: List<OverviewinnerReplacementBehaviorDocument>,
    val skill_documents: List<OverViewinnerSkillDocument>,
    val skill_level_status: String,
    val skill_questions: List<OverviewinnerSkillQuestion>,
    val updated_at: String
)