package com.example.food_nutrition_recipe_app.model.clonemodel

import com.example.aris4autism_project.model.learnermodel.LearnerDiagnosisData
import com.example.aris4autism_project.model.overviewinnerdetail.OverviewinnerProgress

data class OverViewInnerDetailResponse(
    val age: Int,
    val age_unit: String,
    val assessment: OverviewinnerAssessment,
    val behavior_mastered: List<Any>,
    val care_taker_id: Int,
    val created_at: String,
    val created_by: Int,
    val date_of_birth: String,
    val deleted_at: Any,
    val deleted_by: Any,
    val extra_note: Any,
    val gender: String,
    val get_diagnosis_data: ArrayList<LearnerDiagnosisData>,
    val get_learner_icon: OverviewInnerDetailGetLearnerIcon,
    val icon_id: Int,
    val id: Int,
    val name: String,
    val notification_on: Int,
    val past_subscriptions: List<Any>,
    val progress: OverviewinnerProgress,
    val skill_mastered: List<Any>,
    val subscription_id: Int,
    val to_dos: List<Any>,
    val updated_at: String,
    val updated_by: Any,
    val user_subscriptions: OverviewinnerdetailUserSubscriptions,
    val uuid: String
)
{

    fun dobToAge():String{
        return com.example.aris4autism_project.Utils.Utils.dobToAge(date_of_birth)
    }
    override fun toString(): String {
        return "OverViewInnerDetailResponse(age=$age, age_unit='$age_unit', assessment=$assessment, behavior_mastered=$behavior_mastered, care_taker_id=$care_taker_id, created_at='$created_at', created_by=$created_by, date_of_birth='$date_of_birth', deleted_at=$deleted_at, deleted_by=$deleted_by, extra_note=$extra_note, gender='$gender', get_diagnosis_data=$get_diagnosis_data, get_learner_icon=$get_learner_icon, icon_id=$icon_id, id=$id, name='$name', notification_on=$notification_on, past_subscriptions=$past_subscriptions, progress=$progress, skill_mastered=$skill_mastered, subscription_id=$subscription_id, to_dos=$to_dos, updated_at='$updated_at', updated_by=$updated_by, user_subscriptions=$user_subscriptions, uuid='$uuid')"
    }
}
