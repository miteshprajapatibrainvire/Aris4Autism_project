package com.example.aris4autism_project.model

data class BehaviorAssessmentX(
    val behaviors: List<BehaviorXX>,
    val configurations: ConfigurationsX,
    val current_step: String,
    val sensory_issues_questions: List<SensoryIssuesQuestionX>,
    val sensory_stimuli_questions: List<SensoryStimuliQuestionX>
)