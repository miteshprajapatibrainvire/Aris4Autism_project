package com.example.aris4autism_project.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.model.DiagnosisDetailResponse
import com.example.aris4autism_project.repository.UserRespository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiagnosisViewModel(var context: Context): ViewModel() {

    var resultDiagnosisData=MutableLiveData<BaseResponse<DiagnosisDetailResponse>>()
    var userRepository=UserRespository()

    fun getDiagnosisDetailForUser(auth:String,platform:String,version:String)
    {
        resultDiagnosisData.value=BaseResponse.Loading()
        var diagnosisData=userRepository.getDiagnosisUserDetail(auth,platform,version)
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


    }


}