package com.example.aris4autism_project.model

data class BehaviorAssessment(
    val behaviors: List<Behavior>,
    val configurations: Configurations,
    val current_step: String,
    val sensory_issues_questions: List<SensoryIssuesQuestion>,
    val sensory_stimuli_questions: List<SensoryStimuliQuestion>
)