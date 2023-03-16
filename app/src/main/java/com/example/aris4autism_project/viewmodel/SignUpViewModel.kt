package com.example.aris4autism_project.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aris4autism_project.R
import com.example.aris4autism_project.model.authmodel.RequestRegistration
import com.example.aris4autism_project.model.authmodel.ResponseRegistration
import com.example.aris4autism_project.model.countrymodel.CountryResponseModel
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.model.statemodel.StateResponseModel
import com.example.aris4autism_project.network.ApiClient
import com.example.aris4autism_project.repository.Authrepository
import com.example.aris4autism_project.repository.UserRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(val context: Context) : ViewModel() {

    var fullname: String = ""
    var password: String = ""
    var email: String = ""
    var mobile: String = ""
    var dob: String = ""
    var gender: String = ""
    var confirmpassword: String = ""
    var address1: String = ""
    var address2: String = ""
    var streetName: String = ""
    var country: String = ""
    var state: String = ""
    var zipCode: String = ""

    val resultRegistration = MutableLiveData<ResponseHandler<ResponseData<ResponseRegistration>?>>()
    val resoutCountry =
        MutableLiveData<ResponseHandler<ResponseData<List<CountryResponseModel>>?>>()

    val resultStatus = MutableLiveData<ResponseHandler<ResponseData<List<StateResponseModel>>?>>()

    val authRepository = Authrepository(ApiClient.getApiInterface())

    val userRepository = UserRespository(ApiClient.getApiInterface())

    fun getStatusDetails() {
        viewModelScope.launch(Dispatchers.Default) {
            resultStatus.postValue(ResponseHandler.Loading)
            resultStatus.postValue(userRepository.getStatusRepository())
        }
    }

    fun getCountryDetails() {
        viewModelScope.launch(Dispatchers.Default) {
            resoutCountry.postValue(ResponseHandler.Loading)
            resoutCountry.postValue(userRepository.getCountryRepository())
        }
    }

    fun sendRegisterResponse(registration: RequestRegistration) {
        viewModelScope.launch(Dispatchers.IO)
        {
            resultRegistration.postValue(ResponseHandler.Loading)
            resultRegistration.postValue(authRepository.setRegistrationData(registration))
            Log.e("registrationAuth=", authRepository.setRegistrationData(registration).toString())
        }
    }

    private var signUpResult = MutableLiveData<String>()
    fun getSignUpResult(): LiveData<String> = signUpResult

    private var secondSignUpAddress = MutableLiveData<String>()
    fun getSignUpAddressResult(): LiveData<String> = secondSignUpAddress

    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    fun performSignUpValidation() {
        when {
            fullname.isEmpty() && mobile.isEmpty() && email.isEmpty() && gender.isEmpty() && dob.isEmpty() && password.isEmpty() && confirmpassword.isEmpty() -> {
                signUpResult.value = context.getString(R.string.invalidcredential)
            }
            fullname.isEmpty() -> {
                signUpResult.value = context.getString(R.string.fullname)
            }
            mobile.isEmpty() -> {
                signUpResult.value = context.getString(R.string.mobileno)
            }
            mobile.length > 10 || mobile.length < 10 -> {
                signUpResult.value = context.getString(R.string.mobileValidation)
            }
            email.isEmpty() -> {
                signUpResult.value = context.getString(R.string.emailadd)
            }
            !email.trim().matches(emailPattern.toRegex()) -> {
                signUpResult.value = context.getString(R.string.invalidemail)
            }
            gender.isEmpty() -> {
                signUpResult.value = context.getString(R.string.selectgen)
            }
            dob.isEmpty() -> {
                signUpResult.value = context.getString(R.string.dobSelect)
            }
            password.isEmpty() -> {
                signUpResult.value = context.getString(R.string.enterpass)
            }
            confirmpassword.isEmpty() -> {
                signUpResult.value = context.getString(R.string.passconfirm)
                //|| mobile.isNotEmpty() || email.isNotEmpty() || dob.isNotEmpty() || password.isNotEmpty() || confirmpassword.isNotEmpty()
            }
            !password.equals(confirmpassword, true) -> {
                signUpResult.value = context.getString(R.string.passSame)
            }
//                password.trim().matches(passwordPattern.toRegex())->{
//                    signUpResult.value="Password should be minimum of 6 characters and must contain at least one number,one special character, and both uppercase and lowercase letters"
//                }
            password.length < 6 || !password.matches(".*[A-Z].*".toRegex()) || !password.matches(".*[@#\$%^&+=].*".toRegex()) -> {
                signUpResult.value = context.getString(R.string.passwordValidation)
            }
            confirmpassword.length < 6 || !confirmpassword.matches(".*[A-Z].*".toRegex()) || !confirmpassword.matches(
                ".*[@#\$%^&+=].*".toRegex()
            ) -> {
                signUpResult.value = context.getString(R.string.confirmpasswordValidation)
            }

            fullname.isNotEmpty() && mobile.isNotEmpty() && email.isNotEmpty() -> {
                signUpResult.value = context.getString(R.string.validRegistration)
            }
        }
    }

    fun addredDetailsValidation() {
        when {
            address1.isEmpty() && address2.isEmpty() && streetName.isEmpty() && country.isEmpty() && state.isEmpty() && zipCode.isEmpty() -> {
                secondSignUpAddress.value = context.getString(R.string.emptyData)
            }
//            5<Integer.parseInt(zipCode)->
//            {
//                signUpAddress.value="Zip code should be minimum of 5 digits"
//            }
            address1.isEmpty() -> {
                secondSignUpAddress.value = context.getString(R.string.addressLine1Error)
            }
            address2.isEmpty() -> {
                secondSignUpAddress.value = context.getString(R.string.addressLine2Error)
            }
            streetName.isEmpty() -> {
                secondSignUpAddress.value = context.getString(R.string.streetNameError)
            }
            country.isEmpty() -> {
                secondSignUpAddress.value = context.getString(R.string.countryError)
            }
            state.isEmpty() -> {
                secondSignUpAddress.value = context.getString(R.string.stateError)
            }
            zipCode.isEmpty() -> {
                secondSignUpAddress.value = context.getString(R.string.zipCodeError)
            }
            zipCode.length < 5 -> {
                secondSignUpAddress.value = context.getString(R.string.zipCodeValidation)
            }
            address1.isNotEmpty() && address2.isNotEmpty() && streetName.isNotEmpty() && country.isNotEmpty() && state.isNotEmpty() && zipCode.isNotEmpty() -> {
                secondSignUpAddress.value = context.getString(R.string.validCredit)
            }
        }
    }

}