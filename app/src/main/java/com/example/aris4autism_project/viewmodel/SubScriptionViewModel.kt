package com.example.aris4autism_project.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aris4autism_project.model.subscriptionmodel.subscriptionmodelresponse.SubScriptionResponseModel
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.network.ApiClient
import com.example.aris4autism_project.repository.UserRespository
import kotlinx.coroutines.launch

class SubScriptionViewModel(val context: Context):ViewModel()
{
    val resultSubscription=MutableLiveData<ResponseHandler<ResponseData<SubScriptionResponseModel>?>>()
    var userRepository= UserRespository(ApiClient.getApiInterface())

    fun getSubUserDetails()
    {
        viewModelScope.launch {
            resultSubscription.postValue(ResponseHandler.Loading)
            resultSubscription.postValue(userRepository.getSubscriptionDetails())
        }
    }

}