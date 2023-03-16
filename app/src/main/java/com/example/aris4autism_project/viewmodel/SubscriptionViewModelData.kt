package com.example.aris4autism_project.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.api.ApiInterface
import com.example.aris4autism_project.model.SubscriptionListResponse
import com.example.aris4autism_project.network.ApiClient
import com.example.aris4autism_project.repository.UserRespository

class SubscriptionViewModelData(val context:Context):ViewModel()
{
    val userRespository=UserRespository(ApiClient.getApiInterface())
    var subscriptionResult=MutableLiveData<BaseResponse<SubscriptionListResponse>>()

    /*
    fun getSubscriptionResultDetail(auth:String,platform:String,ver:String)
    {
        subscriptionResult.value=BaseResponse.Loading()
        val resultSubscription=userRespository.getSubscriptionDetail(auth,platform,ver)
        resultSubscription.enqueue(object : retrofit2.Callback<SubscriptionListResponse>{
            override fun onResponse(
                call: Call<SubscriptionListResponse>,
                response: Response<SubscriptionListResponse>
            ) {
                if(response.isSuccessful)
                {
                    if(response.code()==200)
                    {
                        subscriptionResult.value=BaseResponse.Success(response.body())
                    }
                    else
                    {
                        subscriptionResult.value=BaseResponse.Error(response.body().toString())
                    }
                }
                else
                {
                    subscriptionResult.value=BaseResponse.Error(response.body().toString())
                }
            }

            override fun onFailure(call: Call<SubscriptionListResponse>, t: Throwable) {
                subscriptionResult.value=BaseResponse.Error(t.toString())
            }

        })
    }
    
     */
}