package com.example.aris4autism_project.model

data class AssessmentXX(
    val _id: String,
    val assessment_id: String,
    val behavior_assessment: BehaviorAssessment,
    val behavior_docs: List<BehaviorDoc>,
    val behavior_level_status: String,
    val created_at: String,
    val developmental_level_status: String,
    val include_doc_tag: List<Any>,
    val learner: LearnerXXX,
    val miscellaneous_docs: MiscellaneousDocs,
    val questions: Questions,
    val remove_skill_tag: List<String>,
    val replacement_behavior_documents: List<ReplacementBehaviorDocument>,
    val skill_documents: List<SkillDocument>,
    val skill_level_status: String,
    val skill_questions: List<SkillQuestion>,
    val updated_at: String
)