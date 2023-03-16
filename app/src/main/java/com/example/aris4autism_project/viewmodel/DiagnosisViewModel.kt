package com.example.aris4autism_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aris4autism_project.model.diagnosismodel.DiagnosisDetailResponseModel
import com.example.aris4autism_project.model.responsemodel.ResponseData
import com.example.aris4autism_project.model.responsemodel.ResponseHandler
import com.example.aris4autism_project.network.ApiClient
import com.example.aris4autism_project.repository.UserRespository
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.launch

class DiagnosisViewModel(var context: Context): ViewModel() {

    var resultDiagnosisData=MutableLiveData<ResponseHandler<ResponseData<List<DiagnosisDetailResponseModel>>?>>()
   // var resultDiagnosisData=MutableLiveData<BaseResponse<DiagnosisDetailResponse>>()
    var userRepository=UserRespository(ApiClient.getApiInterface())


    fun getDiagnosisDetailForUser()
    {
        viewModelScope.launch(coroutineContext) {
//            responseLiveLearnerList.value=ResponseHandler.Loading
            resultDiagnosisData.postValue(ResponseHandler.Loading)
            Log.e("diagnosisDetail=",userRepository.getDiagnosisUserDetail().toString())
            /* Log.e("learnerResponse=",userReposiroty.getLearnerList("Bearer " +authToken,platform,version).toString())
             responseLiveLearnerList.value=userReposiroty.getLearnerList("Bearer " +authToken,platform,version)*/
            resultDiagnosisData.value=userRepository.getDiagnosisUserDetail()
        }

    }

   /* fun getDiagnosisDetailForUser(auth:String,platform:String,version:String)
    {
        resultDiagnosisData.value=BaseResponse.Loading()
        val diagnosisData=userRepository.getDiagnosisUserDetail(auth,platform,version)
        diagnosisData.enqueue(object : Callback<DiagnosisDetailResponse>{
            override fun onResponse(
                call: Call<DiagnosisDetailResponse>,
                response: Response<DiagnosisDetailResponse>
            ) {
                if(response.isSuccessful)
                {
                    if(response.code()==200)
                    {
                        resultDiagnosisData.value=BaseResponse.Success(response.body())
                    }
                    else{
                        resultDiagnosisData.value=BaseResponse.Error(response.body().toString())
                    }
                }
                else
                {
                    resultDiagnosisData.value=BaseResponse.Error(response.body().toString())
                }
            }

            override fun onFailure(call: Call<DiagnosisDetailResponse>, t: Throwable) {
                resultDiagnosisData.value=BaseResponse.Error(t.toString())
            }

        })


    }*/


}