package com.example.aris4autism_project.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aris4autism_project.R
import com.example.aris4autism_project.model.SummaryPassModel
import com.example.aris4autism_project.model.diagnosismodel.DiagnosisDetailResponseModel
import com.example.aris4autism_project.model.learnermodel.BasicDetailBindModel

class BasicDetailValidation(val context: Context):ViewModel() {

    var fullName:String=""
    var gender:String=""
    var dateofbirth:String=""
    var subscription:String=""
    var learnerModelResponse = MutableLiveData<SummaryPassModel>()
    var diagnosisArray=MutableLiveData<ArrayList<DiagnosisDetailResponseModel>>()

    var basicdetailmodel=BasicDetailBindModel()

    var resultBasicDetailValidation=MutableLiveData<String>()
    fun getBasicDetailResult() : MutableLiveData<String> = resultBasicDetailValidation

    fun setBasicDetails(basicModel: BasicDetailBindModel)
    {
        basicdetailmodel=basicModel
    }

    fun getValidateBasicDetalResuit()
    {
        when{

            basicdetailmodel.fullname.isBlank() && basicdetailmodel.gender.isBlank() && basicdetailmodel.dob.isBlank() && basicdetailmodel.subscription.isBlank()->
            {
                resultBasicDetailValidation.value=context.getString(R.string.fillalldetails)
            }

            basicdetailmodel.fullname.isNotEmpty() && basicdetailmodel.gender.isNotEmpty() && basicdetailmodel.dob.isNotEmpty() && basicdetailmodel.subscription.isNotEmpty()->
            {
                resultBasicDetailValidation.value=context.getString(R.string.validcredential)
            }

            basicdetailmodel.fullname.isEmpty()->{
                resultBasicDetailValidation.value=context.getString(R.string.basicDetailName)
            }

            basicdetailmodel.gender.isEmpty()->{
                resultBasicDetailValidation.value=context.getString(R.string.basicDetailGen)
            }

            basicdetailmodel.dob.isEmpty()->{
                resultBasicDetailValidation.value=context.getString(R.string.basicdateofbirth)
            }

            basicdetailmodel.subscription.isEmpty()->{
                resultBasicDetailValidation.value=context.getString(R.string.basicselectsub)
            }
        }
    }

}