package com.example.aris4autism_project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel  : ViewModel() {

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
    var zipPattern="[\\d ]{5,6}"

    private var signUpResult= MutableLiveData<String>()
    fun getSignUpResult(): LiveData<String> = signUpResult

    private var secondSignUpAddress=MutableLiveData<String>()
    fun getSignUpAddressResult():LiveData<String> = secondSignUpAddress

    val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$"

    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    fun performSignUpValidation()
    {
        when {
                fullname.isEmpty() && mobile.isEmpty() && email.isEmpty() && gender.isEmpty() && dob.isEmpty() && password.isEmpty() && confirmpassword.isEmpty() ->
                {
                    signUpResult.value="Empty credential"
                }
                fullname.isEmpty() ->
                {
                    signUpResult.value = "Enter fullname*"
                }
                mobile.isEmpty()->{
                    signUpResult.value="Enter mobile number"
                }
                mobile.length>10 || mobile.length < 10->{
                    signUpResult.value="Mobile number must be of 10 digits"
                }
                email.isEmpty()->{
                    signUpResult.value = "Enter Email address*"
                }
                !email.trim().matches(emailPattern.toRegex())-> {
                    signUpResult.value="Invalid email address*"
                }
                gender.equals("Select") -> {
                    signUpResult.value = "Select Gender*"
                }
                dob.isEmpty()-> {
                    signUpResult.value = "Select Date of birth*"
                }
                password.isEmpty() -> {
                    signUpResult.value = "Please enter your Password"
                }
                password.trim().matches(passwordPattern.toRegex())->{
                    signUpResult.value="Password should be minimum of 6 characters and must contain at least one number,one special character, and both uppercase and lowercase letters"
                }
                confirmpassword.trim().matches(passwordPattern.toRegex())->{
                    signUpResult.value="Confirm Password should be minimum of 6 characters and must contain at least one number,one special character, and both uppercase and lowercase letters"
                }
                confirmpassword.isEmpty() -> {
                    signUpResult.value = "Enter Confirm password*"
                    //|| mobile.isNotEmpty() || email.isNotEmpty() || dob.isNotEmpty() || password.isNotEmpty() || confirmpassword.isNotEmpty()
                }
                fullname.isNotEmpty() && mobile.isNotEmpty() && email.isNotEmpty() ->{
                    signUpResult.value="valid registration"
                }
            }
        }

    fun addredDetailsValidation()
    {
        when{
            address1.isEmpty() && address2.isEmpty() && streetName.isEmpty() && country.isEmpty() && state.isEmpty() && zipCode.isEmpty()->{
                secondSignUpAddress.value="Empty credentials"
            }
//            5<Integer.parseInt(zipCode)->
//            {
//                signUpAddress.value="Zip code should be minimum of 5 digits"
//            }
            address1.isEmpty()->{
                secondSignUpAddress.value="Please enter your Address Line 1"
            }
            address2.isEmpty()->{
                secondSignUpAddress.value="Please enter your Address Line 2"
            }
            streetName.isEmpty()->{
                secondSignUpAddress.value="Please select your State Name"
            }
            country.isEmpty()->{
                secondSignUpAddress.value="Please select your Country"
            }
            state.isEmpty()->{
                secondSignUpAddress.value="Please select your State"
            }
            zipCode.isEmpty()->{
                secondSignUpAddress.value="Please enter a correct Zip Code"
            }
            zipCode.trim().matches(zipPattern.toRegex())->{
                secondSignUpAddress.value="Zip code should be minimum of 5 digits"
            }
            address1.isNotEmpty() && address2.isNotEmpty() && streetName.isNotEmpty() && country.isNotEmpty() && state.isNotEmpty() && zipCode.isNotEmpty()->{
                secondSignUpAddress.value="Valid Credential"
            }
        }
    }



}