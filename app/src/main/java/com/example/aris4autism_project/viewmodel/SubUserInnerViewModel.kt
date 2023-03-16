package com.example.aris4autism_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aris4autism_project.model.subuserinnermodel.SubUserModelInnerResponse
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.network.ApiClient
import com.example.aris4autism_project.repository.UserRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubUserInnerViewModel(val context: Context): ViewModel() {

    val userRepository= UserRespository(ApiClient.getApiInterface())
val  subUserInnerResult=MutableLiveData<ResponseHandler<ResponseData<SubUserModelInnerResponse>?>>()

    fun getSubUserInnerDetails(id:String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            subUserInnerResult.postValue(ResponseHandler.Loading)
            Log.e("responseData=",userRepository.getSubUserInnerDetail(id).toString())
            subUserInnerResult.postValue(userRepository.getSubUserInnerDetail(id))
        }
    }
}