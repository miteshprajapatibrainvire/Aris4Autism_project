package com.example.aris4autism_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.aris4autism_project.Utils.CalenderFormat
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.model.editlearnermodel.SingleUserEditLearnerModel
import com.example.aris4autism_project.model.learnermodel.AddNewLearnerResponse
import com.example.aris4autism_project.model.learnermodel.CreateNewLearnerModel
import com.example.aris4autism_project.model.learnermodel.LearnerReponseModel
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.network.ApiClient
import com.example.aris4autism_project.repository.UserRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class LearnerViewModel(val context: Context) : ViewModelBase() {

    val userReposiroty = UserRespository(ApiClient.getApiInterface())
    val resultEditLearner =
        MutableLiveData<ResponseHandler<ResponseData<SingleUserEditLearnerModel>?>>()
    var resultNewLearner = MutableLiveData<ResponseHandler<ResponseData<AddNewLearnerResponse>?>>()
    var responseLiveLearnerList =
        MutableLiveData<ResponseHandler<ResponseData<LearnerReponseModel>?>>()

    fun getEditLearnerResponse(id: String) {
        viewModelScope.launch(Dispatchers.Default) {
            resultEditLearner.postValue(ResponseHandler.Loading)
            Log.e("getEditLearnerDetails=", userReposiroty.getEditLearnerDetails(id).toString())
            resultEditLearner.postValue(userReposiroty.getEditLearnerDetails(id))
        }
    }

    fun addNewLearner(addLearner: CreateNewLearnerModel) {
        viewModelScope.launch(Dispatchers.Default)
        {
            resultNewLearner.postValue(ResponseHandler.Loading)
            resultNewLearner.postValue(userReposiroty.addNewLearnerDetails(addLearner))
        }
    }

    fun getLearnerList() {
        viewModelScope.launch(coroutineContext) {
            responseLiveLearnerList.postValue(ResponseHandler.Loading)
            // Log.e("learnerResponse=",userReposiroty.getLearnerList("Bearer " +authToken,platform,version).toString())
            responseLiveLearnerList.value = userReposiroty.getLearnerList()
        }

    }

    fun dobToAge(dob: String): String {
        return if (!Utils.checkDateFormat(dob, CalenderFormat.MM_DD_YYYY_D.type)) {
            val formatter: DateFormat =
                SimpleDateFormat(CalenderFormat.YYYY_MM_DD.type, Locale.ROOT)
            val formatter2: DateFormat =
                SimpleDateFormat(CalenderFormat.MM_DD_YYYY_D.type, Locale.ROOT)
            val date = formatter.parse(dob) as Date
            val date2 = formatter2.format(date)
            Utils.calculateAge(date2)
        } else {
            Utils.calculateAge(dob)
        }
    }


}