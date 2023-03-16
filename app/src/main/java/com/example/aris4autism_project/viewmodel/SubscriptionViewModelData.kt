package com.example.aris4autism_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aris4autism_project.model.subscriptionplanmodel.SubscriptionPlanListModelResponse
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.network.ApiClient
import com.example.aris4autism_project.repository.UserRespository
import kotlinx.coroutines.launch

class SubscriptionViewModelData(val context:Context):ViewModel()
{
    val userRespository=UserRespository(ApiClient.getApiInterface())
    var subscriptionResult=MutableLiveData<ResponseHandler<ResponseData<SubscriptionPlanListModelResponse>?>>()

    fun getSubscriptionResultDetail()
    {
        viewModelScope.launch {
            subscriptionResult.postValue(ResponseHandler.Loading)
            Log.e("subscriptionplan=",userRespository.getSubscriptionDetail().toString())
            subscriptionResult.postValue(userRespository.getSubscriptionDetail())
        }
    }

}