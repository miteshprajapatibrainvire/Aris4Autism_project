package com.example.aris4autism_project.model.overviewmodel


import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.model.learnermodel.UserSubscriptions
import com.google.gson.annotations.SerializedName

data class OverViewListData(

    @SerializedName("age")
    val age: Int,
    @SerializedName("assessment")
    val assessment: Any,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("get_learner_icon")
    val getLearnerIcon: GetOverViewLearnerIcon,
    @SerializedName("icon_id")
    val iconId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("srNum")
    val srNum: Int,
    @SerializedName("sub_user_ids")
    val subUserIds: List<OverViewSubUserId>,
    @SerializedName("subscription_id")
    val subscriptionId: Int,
    @SerializedName("user_subscriptions")
    val userSubscriptions: Usersubscription,
    @SerializedName("uuid")
    val uuid: String
):java.io.Serializable
{

    override fun hashCode(): Int {
        var result:Int=this.age
        var assessment:Any=this.assessment
        var dateOfBirth:String=this.dateOfBirth
        var gender:String=this.gender
        var genderIcon:GetOverViewLearnerIcon=this.getLearnerIcon
        var iconId:Int=this.iconId
        var id:Int=this.id
        var name:String=this.name
        var srNum:Int=this.srNum
       // var listData:List<OverViewSubUserId>=this.subUserIds
        var subscriptionId:Int=this.subscriptionId
        var userSubscriptions:Any=this.userSubscriptions
        var uuid:String=this.uuid

        return result
    }

    fun getUserSubscriptionMonthDetail():String{
        if(userSubscriptions!=null)
        {
            return "null"
        }
        else
        {
            return "null"
        }
    }


    fun getDob():String
    {
        return "DOB :"+ this.dateOfBirth
    }
    fun fullDate():String{
        return "null"
    }

   open fun getStatusBackground():Boolean
   {
        if(userSubscriptions!=null)
        {
            return true
        }
        else
        {
            return false
        }
    }

    fun dobToAge():String{
        return Utils.dobToAge(dateOfBirth)
    }

}
