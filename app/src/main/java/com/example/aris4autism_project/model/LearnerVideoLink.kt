package com.example.aris4autism_project.model

data class LearnerVideoLink(val link:String,val title:String) {
    override fun toString(): String {
        return "LearnerVideoLink(link='$link', title='$title')"
    }
}