package com.example.aris4autism_project.model.overviewinnerdetail

import com.example.aris4autism_project.Utils.CalenderFormat
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.model.learnermodel.LearnerDiagnosisData
import com.example.food_nutrition_recipe_app.model.clonemodel.OverviewInnerDetailGetLearnerIcon
import com.example.food_nutrition_recipe_app.model.clonemodel.OverviewinnerAssessment
import com.example.food_nutrition_recipe_app.model.clonemodel.OverviewinnerdetailUserSubscriptions
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class Overviewdata(
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
    fun getDob():String {
        return "DOB : "+date_of_birth
    }

    fun dobToAge(): String {
        return if (!Utils.checkDateFormat(date_of_birth, CalenderFormat.MM_DD_YYYY_D.type)) {
            val formatter: DateFormat =
                SimpleDateFormat(CalenderFormat.YYYY_MM_DD.type, Locale.ROOT)
            val formatter2: DateFormat =
                SimpleDateFormat(CalenderFormat.MM_DD_YYYY_D.type, Locale.ROOT)
            val date = formatter.parse(date_of_birth) as Date
            val date2 = formatter2.format(date)
            Utils.calculateAge(date2)
        } else {
            Utils.calculateAge(date_of_birth)
        }
    }
}