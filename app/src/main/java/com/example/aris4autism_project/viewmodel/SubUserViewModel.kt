package com.example.aris4autism_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.model.editsubuserdetailmodel.EditSubUserDetailsModel
import com.example.aris4autism_project.model.subusermodel.SubUserResponseModel
import com.example.aris4autism_project.network.ApiClient
import com.example.aris4autism_project.repository.UserRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubUserViewModel(val context: Context):ViewModel()
{

    var subUserResult: MutableLiveData<ResponseHandler<ResponseData<SubUserResponseModel>?>> = MutableLiveData()
    val userRepository=UserRespository(ApiClient.getApiInterface())
    var subUserDetailResult=MutableLiveData<ResponseHandler<ResponseData<EditSubUserDetailsModel>?>>()
    fun getSubUserEditDetailResult(id:String)
    {
        viewModelScope.launch {
            subUserDetailResult.postValue(ResponseHandler.Loading)
            subUserDetailResult.postValue(userRepository.getSubUserEditDetails(id))
        }
    }

    fun getSubUserDetailsModel()
    {
        viewModelScope.launch(Dispatchers.Default)
        {
            subUserResult.postValue(ResponseHandler.Loading)
            Log.e("subuserDetail=",userRepository.getSubUserDetails().toString())
            subUserResult.postValue(userRepository.getSubUserDetails())
        }
    }

}