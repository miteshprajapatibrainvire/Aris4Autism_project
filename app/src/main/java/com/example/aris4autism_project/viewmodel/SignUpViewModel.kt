package com.example.aris4autism_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.R
import com.example.aris4autism_project.model.RequestRegistration
import com.example.aris4autism_project.model.ResponseCountryModel
import com.example.aris4autism_project.model.ResponseRegistration
import com.example.aris4autism_project.model.ResponseStateModel
import com.example.aris4autism_project.repository.Authrepository
import com.example.aris4autism_project.repository.UserRespository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel(val context:Context)  : ViewModel() {

    var fullname:String=""
    var password:String=""
    var email:String=""
    var mobile:String=""
    var dob:String=""
    var gender:String=""
    var confirmpassword:String=""
    var address1:String=""
    var address2:String=""
    var streetName:String=""
    var country:String=""
    var state:String=""
    var zipCode:String=""

    val resultRegistration:MutableLiveData<BaseResponse<ResponseRegistration>> = MutableLiveData()
    val resultcountry:MutableLiveData<BaseResponse<ResponseCountryModel>> = MutableLiveData()
    val resultStates:MutableLiveData<BaseResponse<ResponseStateModel>> =MutableLiveData()

    val authRepository=Authrepository()
    val userRepository=UserRespository()

    fun getStatusDetails()
    {
        val resultState=userRepository.getStatusRepository()
        resultStates.value=BaseResponse.Loading()
        resultState.enqueue(object:Callback<ResponseStateModel>{
            override fun onResponse(
                call: Call<ResponseStateModel>,
                response: Response<ResponseStateModel>
            ) {
                if(response.isSuccessful) {
                    if (response.code() == 200) {
                        resultStates.value=BaseResponse.Success(response.body())
                    }
                }
                else
                {
                    resultStates.value=BaseResponse.Error(response.body().toString())
                }
            }

            override fun onFailure(call: Call<ResponseStateModel>, t: Throwable) {
                resultStates.value=BaseResponse.Error(t.toString())
            }

        })


    }

    fun getCountryDetails()
    {
        val resultCon=userRepository.getCountryRepository()
        resultcountry.value=BaseResponse.Loading()
        resultCon.enqueue(object:Callback<ResponseCountryModel>{
            override fun onResponse(
                call: Call<ResponseCountryModel>,
                response: Response<ResponseCountryModel>
            ) {
                if(response.isSuccessful)
                {
                    if(response.code()==200)
                    {
                        resultcountry.value=BaseResponse.Success(response.body())
                       // Log.e("countryresponseBody=",response.body().toString())
                    }
                }
                else
                {
                    resultcountry.value=BaseResponse.Error(response.body().toString())
                }
            }
            override fun onFailure(call: Call<ResponseCountryModel>, t: Throwable) {
                resultcountry.value=BaseResponse.Error(t.toString())
            }
        })
    }


    fun sendRegisterResponse(registerModel: RequestRegistration)
    {
        val resultData=authRepository.setRegistrationData(registerModel)
//      var resultData=userRepository.getLoginData(RequestLogin("ascscdscdscds1111111","Android",email="faizan9dec@mailinator.com",password="Test@123"))
        resultRegistration.value=BaseResponse.Loading()
        resultData.enqueue(object : Callback<ResponseRegistration> {
            override fun onResponse(call: Call<ResponseRegistration>, response: Response<ResponseRegistration>) {
                if(response.isSuccessful)
                {
                    if(response.code()==200)
                    {
                        resultRegistration.value=BaseResponse.Success(response.body())
                        Log.e("responseBody=",response.body().toString())
                    }
                }
                else
                {
                    resultRegistration.value=BaseResponse.Error(response.body().toString())
                }
            }

            override fun onFailure(call: Call<ResponseRegistration>, t: Throwable) {
                resultRegistration.value=BaseResponse.Error(t.toString())
            }

        })
    }

    private var signUpResult= MutableLiveData<String>()
    fun getSignUpResult(): LiveData<String> = signUpResult

    private var secondSignUpAddress=MutableLiveData<String>()
    fun getSignUpAddressResult():LiveData<String> = secondSignUpAddress


    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    fun performSignUpValidation()
    {
        when {
                fullname.isEmpty() && mobile.isEmpty() && email.isEmpty() && gender.isEmpty() && dob.isEmpty() && password.isEmpty() && confirmpassword.isEmpty() ->
                {
                    signUpResult.value=context.getString(R.string.invalidcredential)
                }
                fullname.isEmpty() ->
                {
                    signUpResult.value = context.getString(R.string.fullname)
                }
                mobile.isEmpty()->{
                    signUpResult.value=context.getString(R.string.mobileno)
                }
                mobile.length>10 || mobile.length < 10->{
                    signUpResult.value=context.getString(R.string.mobileValidation)
                }
                email.isEmpty()->{
                    signUpResult.value = context.getString(R.string.emailadd)
                }
                !email.trim().matches(emailPattern.toRegex())-> {
                    signUpResult.value=context.getString(R.string.invalidemail)
                }
                gender.isEmpty() -> {
                    signUpResult.value = context.getString(R.string.selectgen)
                }
                dob.isEmpty()-> {
                    signUpResult.value = context.getString(R.string.dobSelect)
                }
                password.isEmpty() -> {
                    signUpResult.value = context.getString(R.string.enterpass)
                }
                confirmpassword.isEmpty() -> {
                    signUpResult.value = context.getString(R.string.passconfirm)
                    //|| mobile.isNotEmpty() || email.isNotEmpty() || dob.isNotEmpty() || password.isNotEmpty() || confirmpassword.isNotEmpty()
                }
                !password.equals(confirmpassword,true)->{
                    signUpResult.value=context.getString(R.string.passSame)
                }
//                password.trim().matches(passwordPattern.toRegex())->{
//                    signUpResult.value="Password should be minimum of 6 characters and must contain at least one number,one special character, and both uppercase and lowercase letters"
//                }
                password.length<6 || !password.matches(".*[A-Z].*".toRegex()) || !password.matches(".*[@#\$%^&+=].*".toRegex())->{
                    signUpResult.value=context.getString(R.string.passwordValidation)
                }
                confirmpassword.length<6 || !confirmpassword.matches(".*[A-Z].*".toRegex()) || !confirmpassword.matches(".*[@#\$%^&+=].*".toRegex())->{
                    signUpResult.value=context.getString(R.string.confirmpasswordValidation)
                }

                fullname.isNotEmpty() && mobile.isNotEmpty() && email.isNotEmpty() ->{
                    signUpResult.value=context.getString(R.string.validRegistration)
                }
            }
        }

    fun addredDetailsValidation()
    {
        when{
            address1.isEmpty() && address2.isEmpty() && streetName.isEmpty() && country.isEmpty() && state.isEmpty() && zipCode.isEmpty()->{
                secondSignUpAddress.value=context.getString(R.string.emptyData)
            }
//            5<Integer.parseInt(zipCode)->
//            {
//                signUpAddress.value="Zip code should be minimum of 5 digits"
//            }
            address1.isEmpty()->{
                secondSignUpAddress.value=context.getString(R.string.addressLine1Error)
            }
            address2.isEmpty()->{
                secondSignUpAddress.value=context.getString(R.string.addressLine2Error)
            }
            streetName.isEmpty()->{
                secondSignUpAddress.value=context.getString(R.string.streetNameError)
            }
            country.isEmpty()->{
                secondSignUpAddress.value=context.getString(R.string.countryError)
            }
            state.isEmpty()->{
                secondSignUpAddress.value=context.getString(R.string.stateError)
            }
            zipCode.isEmpty()->{
                secondSignUpAddress.value=context.getString(R.string.zipCodeError)
            }
            zipCode.length<5->{
                secondSignUpAddress.value=context.getString(R.string.zipCodeValidation)
            }
            address1.isNotEmpty() && address2.isNotEmpty() && streetName.isNotEmpty() && country.isNotEmpty() && state.isNotEmpty() && zipCode.isNotEmpty()->{
                secondSignUpAddress.value=context.getString(R.string.validCredit)
            }
        }
    }

}