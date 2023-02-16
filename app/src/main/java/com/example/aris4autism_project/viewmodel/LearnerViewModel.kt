package com.example.aris4autism_project.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.Utils.CalenderFormat
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.model.LearnerResponse
import com.example.aris4autism_project.repository.UserRespository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class LearnerViewModel(val context: Context): ViewModel() {

    val resultLearner=MutableLiveData<BaseResponse<LearnerResponse>>()
    val userReposiroty=UserRespository()

    fun getLearnerList(authToken:String,platform:String,version:String)
    {
        resultLearner.value=BaseResponse.Loading()

        val resultData=userReposiroty.getLearnerDetail(authToken,platform,version)
        resultData.enqueue(object : Callback<LearnerResponse>{
            override fun onResponse(
                call: Call<LearnerResponse>,
                response: Response<LearnerResponse>
            ) {
                if(response.isSuccessful)
                {
                    if(response.code()==200)
                    {
                        resultLearner.value=BaseResponse.Success(response.body())
                    }
                }
                else
                {
                    resultLearner.value=BaseResponse.Error(response.body().toString())
                }
            }

            override fun onFailure(call: Call<LearnerResponse>, t: Throwable) {
                resultLearner.value=BaseResponse.Error(t.toString())
            }

        })

    }

     fun dobToAge(dob: String): String
    {
        return if (!Utils.checkDateFormat(dob, CalenderFormat.MM_DD_YYYY_D.type)) {
            val formatter: DateFormat =
                SimpleDateFormat(CalenderFormat.YYYY_MM_DD.type, Locale.ROOT)
            val formatter2: DateFormat =
                SimpleDateFormat(CalenderFormat.MM_DD_YYYY_D.type, Locale.ROOT)
            val date = formatter.parse(dob) as Date
            val date2 = formatter2.format(date)
            Utils.calculateAge(date2)
        } else {
            Utils.calculateAge(dob)
        }
    }


}