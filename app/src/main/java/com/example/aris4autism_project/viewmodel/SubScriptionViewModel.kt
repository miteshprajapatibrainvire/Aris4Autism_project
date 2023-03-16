package com.example.aris4autism_project.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aris4autism_project.model.subscriptionmodel.subscriptionmodelresponse.SubScriptionResponseModel
import com.example.aris4autism_project.model.responsemodel.ResponseData
import com.example.aris4autism_project.model.responsemodel.ResponseHandler
import com.example.aris4autism_project.network.ApiClient
import com.example.aris4autism_project.repository.UserRespository
import kotlinx.coroutines.launch

class SubScriptionViewModel(val context: Context):ViewModel()
{
    val resultSubscription=MutableLiveData<ResponseHandler<ResponseData<SubScriptionResponseModel>?>>()

    //val resultSubscription=MutableLiveData<BaseResponse<SubScriptionResponse>>()
    var userRepository= UserRespository(ApiClient.getApiInterface())

    fun getSubUserDetails()
    {
        viewModelScope.launch {
            resultSubscription.postValue(ResponseHandler.Loading)
            resultSubscription.postValue(userRepository.getSubscriptionDetails())
        }
    }

//    fun getSubUserDetails(auth:String,platform:String,version:String)
//    {
//        resultSubscription.value=BaseResponse.Loading()
//        val resultData=userRepository.getSubscriptionDetails(auth,platform,version)
//        resultData.enqueue(object : retrofit2.Callback<SubScriptionResponse> {
//            override fun onResponse(
//                call: Call<SubScriptionResponse>,
//                response: Response<SubScriptionResponse>
//            ) {
//
//                if(response.isSuccessful)
//                {
//                    if(response.code()==200)
//                    {
//                        Log.e("subscriptionResponse=",response.body().toString())
//                        resultSubscription.value=BaseResponse.Success(response.body())
//                    }
//                }
//                else
//                {
//                    resultSubscription.value=BaseResponse.Error(response.body().toString())
//                }
//            }
//
//            override fun onFailure(call: Call<SubScriptionResponse>, t: Throwable) {
//               resultSubscription.value=BaseResponse.Error(t.toString())
//            }
//        })
//    }
}