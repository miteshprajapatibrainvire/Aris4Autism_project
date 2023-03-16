package com.example.aris4autism_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aris4autism_project.model.diagnosismodel.DiagnosisDetailResponseModel
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.network.ApiClient
import com.example.aris4autism_project.repository.UserRespository
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.launch

class DiagnosisViewModel(var context: Context): ViewModel() {

    var resultDiagnosisData=MutableLiveData<ResponseHandler<ResponseData<List<DiagnosisDetailResponseModel>>?>>()
    var userRepository=UserRespository(ApiClient.getApiInterface())


    fun getDiagnosisDetailForUser()
    {
        viewModelScope.launch(coroutineContext) {
            resultDiagnosisData.postValue(ResponseHandler.Loading)
            Log.e("diagnosisDetail=",userRepository.getDiagnosisUserDetail().toString())
            resultDiagnosisData.value=userRepository.getDiagnosisUserDetail()
        }

    }

}