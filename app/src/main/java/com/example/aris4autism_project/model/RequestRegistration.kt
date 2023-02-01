package com.example.aris4autism_project.model

data class RequestRegistration(
val email:String,
val password:String,
val phone_number:String,
val name:String,
val gender:String,
val country_id:String,
val state_id:String,
val date_of_birth:String,
val address_line_1:String,
val address_line_2:String,
val street:String,
val zip_code:String,
val icon_id:String,
val device_token:String,
val device_type:String
) {

    override fun toString(): String {
        return "RequestRegistration(email='$email', password='$password', phone_number='$phone_number', name='$name', gender='$gender', country_id='$country_id', state_id='$state_id', date_of_birth='$date_of_birth', address_line_1='$address_line_1', address_line_2='$address_line_2', street='$street', zip_code='$zip_code', icon_id='$icon_id', device_token='$device_token', device_type='$device_type')"
    }

}