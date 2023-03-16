package com.example.aris4autism_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aris4autism_project.model.subuserinnermodel.SubUserModelInnerResponse
import com.example.aris4autism_project.model.responsemodel.ResponseData
import com.example.aris4autism_project.model.responsemodel.ResponseHandler
import com.example.aris4autism_project.network.ApiClient
import com.example.aris4autism_project.repository.UserRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubUserInnerViewModel(val context: Context): ViewModel() {

    val userRepository= UserRespository(ApiClient.getApiInterface())

//    var subUserInnerResult: MutableLiveData<BaseResponse<SubUserInnerDetailModelResponse>> = MutableLiveData()
    //var subUserInnerResult: MutableLiveData<BaseResponse<SubUserModelInnerResponse>> = MutableLiveData()
var subUserInnerResult=MutableLiveData<ResponseHandler<ResponseData<SubUserModelInnerResponse>?>>()
    fun getSubUserInnerDetails(id:String)
    {
        viewModelScope.launch(Dispatchers.IO) {
//            responseLiveLearnerList.value=ResponseHandler.Loading
            subUserInnerResult.postValue(ResponseHandler.Loading)
            /* Log.e("learnerResponse=",userReposiroty.getLearnerList("Bearer " +authToken,platform,version).toString())
             responseLiveLearnerList.value=userReposiroty.getLearnerList("Bearer " +authToken,platform,version)*/
            Log.e("responseData=",userRepository.getSubUserInnerDetail(id).toString())
            subUserInnerResult.postValue(userRepository.getSubUserInnerDetail(id))
        }
    }

   /* fun getSubUserInnerDetails(id:String,authToken: String,platform: String,ver:String)
    {
        subUserInnerResult.value=BaseResponse.Loading()
        val resultInnerSubUser=userRepository.getSubUserInnerDetail(id,authToken,platform,ver)
        resultInnerSubUser.enqueue(object: Callback<SubUserModelInnerResponse> {
            override fun onResponse(
                call: Call<SubUserModelInnerResponse>,
                response: Response<SubUserModelInnerResponse>
            ) {
                if(response.isSuccessful)
                {
                    if(response.code()==200)
                    {
                        Log.e("subuserResponse=",response.body().toString())
                        subUserInnerResult.value=BaseResponse.Success(response.body())
                    }
                    else
                    {
                        subUserInnerResult.value=BaseResponse.Error(response.body().toString())
                    }
                }
                else
                {
                    subUserInnerResult.value=BaseResponse.Error(response.body().toString())
                }
            }

            override fun onFailure(call: Call<SubUserModelInnerResponse>, t: Throwable)
            {
                subUserInnerResult.value=BaseResponse.Error(t.toString())
            }

        })
    }*/
}