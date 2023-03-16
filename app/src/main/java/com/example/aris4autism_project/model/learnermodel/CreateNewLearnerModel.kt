package com.example.aris4autism_project.model.learnermodel

data class CreateNewLearnerModel(
    var icon_id:String,
    val name:String,
    var gender:String,
    var date_of_birth:String,
    var age:String,
    var subscription_id:String,
    var diagnosis_id:ArrayList<String>,
    var extra_note:String=""
)
