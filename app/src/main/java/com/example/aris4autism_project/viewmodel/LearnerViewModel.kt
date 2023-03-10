package com.example.aris4autism_project.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.Utils.CalenderFormat
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.api.ApiInterface
import com.example.aris4autism_project.model.*
import com.example.aris4autism_project.model.learnermodel.LearnerReponseModel
import com.example.aris4autism_project.repository.UserRespository
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class LearnerViewModel(val context: Context) : ViewModelBase() {

    val resultLearner = MutableLiveData<BaseResponse<LearnerResponse>>()
   val userReposiroty = UserRespository(ApiInterface.getInterfaceData())
    val resultEditLearner = MutableLiveData<BaseResponse<EditLearnerModelResponse>>()
    val resultNewLearner=MutableLiveData<BaseResponse<AddNewLearnerResponse>>()
    var responseLiveLearnerList=MutableLiveData<ResponseHandler<ResponseData<LearnerReponseModel>?>>()

    fun addNewLearner(addLearner:CreateNewLearnerModel,auth:String,platform:String,version:String)
    {
        resultNewLearner.value=BaseResponse.Loading()

       /* val resultEdit=userReposiroty.addNewLearnerDetails(addLearner,auth,platform,version)
        resultEdit.enqueue(object : Callback<AddNewLearnerResponse>{
            override fun onResponse(
                call: Call<AddNewLearnerResponse>,
                response: Response<AddNewLearnerResponse>
            ) {
                Log.e("Directresponse=",response.body().toString())
                if(response.isSuccessful)
                {
                    if(response.code()==200)
                    {
                        Log.e("response=",response.body().toString())
                        resultNewLearner.value=BaseResponse.Success(response.body())
                    }
                    else{
                        resultNewLearner.value=BaseResponse.Error(response.body().toString())
                    }
                }
                else
                {
                    resultNewLearner.value=BaseResponse.Error(response.body().toString())
                }
            }
            override fun onFailure(call: Call<AddNewLearnerResponse>, t: Throwable) {
              resultNewLearner.value=BaseResponse.Error(t.toString())
            }

        })



    }


    fun getEditLearnerResponse(id: String, auth: String, platform: String, version: String) {
        resultEditLearner.value = BaseResponse.Loading()
        val resultEdit = userReposiroty.getEditLearnerDetails(id, auth, platform, version)
        resultEdit.enqueue(object : Callback<EditLearnerModelResponse> {
            override fun onResponse(
                call: Call<EditLearnerModelResponse>,
                response: Response<EditLearnerModelResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.code() == 200) {
                        resultEditLearner.value = BaseResponse.Success(response.body())
                    }
                } else {
                    resultEditLearner.value = BaseResponse.Error(response.body().toString())
                }
            }

            override fun onFailure(call: Call<EditLearnerModelResponse>, t: Throwable) {
                resultEditLearner.value = BaseResponse.Error(t.toString())
            }
        })

        */

    }

     fun getLearnerList(authToken:String, platform:String, version:String)
    {
        viewModelScope.launch(coroutineContext) {
//            responseLiveLearnerList.value=ResponseHandler.Loading
            responseLiveLearnerList.postValue(ResponseHandler.Loading)
            responseLiveLearnerList.value=userReposiroty.getLearnerList("Bearer " +authToken,platform,version)
        }

    }

//    fun getLearnerList(authToken: String, platform: String, version: String) {
//        resultLearner.value = BaseResponse.Loading()
//
//        val resultData = userReposiroty.getLearnerDetail(authToken, platform, version)
//        resultData.enqueue(object : Callback<LearnerResponse> {
//            override fun onResponse(
//                call: Call<LearnerResponse>,
//                response: Response<LearnerResponse>
//            ) {
//                if (response.isSuccessful) {
//                    if (response.code() == 200) {
//                        resultLearner.value = BaseResponse.Success(response.body())
//                    }
//                } else {
//                    resultLearner.value = BaseResponse.Error(response.body().toString())
//                }
//            }
//
//            override fun onFailure(call: Call<LearnerResponse>, t: Throwable) {
//                resultLearner.value = BaseResponse.Error(t.toString())
//            }
//
//        })
//
//    }

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