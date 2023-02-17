package com.example.aris4autism_project.model

data class AssessmentXXX(
    val _id: String,
    val assessment_id: String,
    val behavior_assessment: BehaviorAssessmentX,
    val behavior_docs: List<BehaviorDocX>,
    val behavior_level_status: String,
    val created_at: String,
    val developmental_level_status: String,
    val include_doc_tag: List<Any>,
    val learner: LearnerXXXX,
    val miscellaneous_docs: MiscellaneousDocsX,
    val questions: QuestionsX,
    val remove_skill_tag: List<String>,
    val replacement_behavior_documents: List<ReplacementBehaviorDocumentX>,
    val skill_documents: List<SkillDocumentX>,
    val skill_level_status: String,
    val skill_questions: List<SkillQuestionX>,
    val updated_at: String
)