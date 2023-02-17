package com.example.aris4autism_project.model

data class Question(
    val direct_question_doc_id: Int,
    val general_doc_id: Int,
    val hyper_doc_id: Int,
    val hypo_doc_id: Int,
    val identifier: String,
    val is_none_of_above: String,
    val is_this_sensory: String,
    val option_type: String,
    val options: List<Option>,
    val question: String,
    val question_id: Int,
    val question_status: String,
    val question_uuid: String,
    val type: String,
    val type_id: Int,
    val type_name: String
)