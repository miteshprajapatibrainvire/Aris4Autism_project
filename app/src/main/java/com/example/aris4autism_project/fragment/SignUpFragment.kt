package com.example.aris4autism_project.fragment

import android.app.DatePickerDialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.ProfileAdapter
import com.example.aris4autism_project.databinding.FragmentSingUpBinding
import com.example.aris4autism_project.model.ProfileModel
import com.example.aris4autism_project.viewmodel.SignUpViewModel
import java.util.*
import kotlin.collections.ArrayList

class SignUpFragment : Fragment() {

    lateinit var binding:FragmentSingUpBinding
    lateinit var  viewModel: SignUpViewModel
    lateinit var adpProfile:ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSingUpBinding.inflate(layoutInflater,container,false)

        val languages = resources.getStringArray(R.array.genStr)
        val adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_list_item_1, languages)
        binding.spGender.setAdapter(adapter)
        viewModel = ViewModelProvider(requireActivity()).get(SignUpViewModel::class.java)
        binding.signUpModel=viewModel
        binding.lifecycleOwner=this
        val arrayProfile=ArrayList<ProfileModel>()

        arrayProfile.add(ProfileModel(R.drawable.profileimg1,false))
        arrayProfile.add(ProfileModel(R.drawable.profile2img,false))
        arrayProfile.add(ProfileModel(R.drawable.profileimg3,false))
        arrayProfile.add(ProfileModel(R.drawable.profileimg4,false))
        arrayProfile.add(ProfileModel(R.drawable.profileimggirl1,false))
        arrayProfile.add(ProfileModel(R.drawable.profilegirlimg2,false))
        arrayProfile.add(ProfileModel(R.drawable.profilegirlimg3,false))
        arrayProfile.add(ProfileModel(R.drawable.profileimggirl4,false))

        adpProfile=ProfileAdapter(arrayProfile)
        binding.recyId.layoutManager=GridLayoutManager(requireActivity(),4)
        binding.recyId.adapter=adpProfile

        viewModel.getSignUpResult().observe(requireActivity()) { result ->

            if (result.toString().equals("Enter fullname*")) {

                binding.txLayoutFullName.hint=resources.getString(R.string.enterfullaname)
                binding.txLayoutFullName.error = resources.getString(R.string.enterfullaname)

            }
            else if (result.toString().equals("Enter mobile number*"))
            {

                binding.txLayoutFullName.hint=resources.getString(R.string.entermobile)
                binding.txLayoutFullName.error = resources.getString(R.string.entermobile)

            } else if (result.toString().equals("Only 10 digit allow*"))
            {
                binding.txLayoutMobileNumber.hint=resources.getString(R.string.mobileNumber)
                binding.txLayoutMobileNumber.error = resources.getString(R.string.mobileNumber)

            } else if (result.toString().equals("Enter Email address*")) {

                binding.txlayoutEmailData.hint=resources.getString(R.string.emailAddress)
                binding.txlayoutEmailData.error = resources.getString(R.string.emailAddress)

            } else if (result.toString().equals("Invalid email address*")) {
                binding.txlayoutEmailData.hint=resources.getString(R.string.invalidEmail)
                binding.txlayoutEmailData.error = resources.getString(R.string.invalidEmail)

            } else if (result.toString().equals("Select Gender*")) {
                binding.txlayoutGender.hint = resources.getString(R.string.genderStr)
                binding.txlayoutGender.error = resources.getString(R.string.genderStr)

            } else if (result.toString().equals("Select Date of birth*")) {

                binding.txLayoutdate.hint = resources.getString(R.string.dobStr)
                binding.txLayoutdate.error = resources.getString(R.string.dobStr)

            } else if (result.toString().equals("Enter Password!")) {

                binding.txlayoutpassword.hint = resources.getString(R.string.passwordStr)
                binding.txLayoutdate.error=resources.getString(R.string.passwordStr)

            } else if (result.toString().equals("Password should be minimum of 6 characters and must contain at least one number,one special character, and both uppercase and lowercase letters")) {

                binding.txlayoutpassword.hint = resources.getString(R.string.passwordValidation)
                binding.txLayoutdate.error=resources.getString(R.string.passwordValidation)

            } else if (result.toString().equals("Enter Confirm password!")) {

                binding.txlayoutConfirmpassword.hint=resources.getString(R.string.passwordStr)
                binding.txLayoutdate.error=resources.getString(R.string.passwordStr)

            }
            else if (result.toString().equals("Valid Credentials!"))
            {
                Toast.makeText(requireActivity(), "Valid Credentials", Toast.LENGTH_SHORT).show()
            }

        }

        binding.iddob.setOnClickListener {
            clickDatePicker()
        }

        return binding.root
    }

    private fun clickDatePicker()
    {
        val myCalander= Calendar.getInstance()
        val year=myCalander.get(Calendar.YEAR)
        val month=myCalander.get(Calendar.MONTH)
        val day=myCalander.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { datePicker, y, m, d ->
            val monthData=m+1
            val strData:String=d.toString()+"/"+monthData.toString()+"/"+y.toString()
           binding.iddob.setText(strData)

        },year,month,day)
        dpd.datePicker.maxDate=System.currentTimeMillis() - 8640000
        dpd.show()
    }


}