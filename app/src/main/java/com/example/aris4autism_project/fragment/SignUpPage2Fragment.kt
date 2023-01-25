package com.example.aris4autism_project.fragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.FragmentSignUpPage2Binding
import com.example.aris4autism_project.viewmodel.SignUpViewModel
import com.google.android.material.textfield.TextInputLayout

class SignUpPage2Fragment : Fragment() {

    lateinit var binding:FragmentSignUpPage2Binding
    lateinit var viewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSignUpPage2Binding.inflate(inflater,container,false)

        viewModel=ViewModelProvider(requireActivity()).get(SignUpViewModel::class.java)
        binding.signUpModel = viewModel
        binding.lifecycleOwner = this

        viewModel.getSignUpAddressResult().observe(requireActivity(),{result->

//            Log.e("resultData=",result.toString())
            Toast.makeText(requireContext(), result.toString(), Toast.LENGTH_SHORT).show()

            if(result.toString().equals(resources.getString(R.string.emptyData)))
            {
                binding.txlayoutaddress1.isErrorEnabled=true
                binding.txlayoutaddress1.error=resources.getString(R.string.addressLine1Error)
                setBorderColor(binding.txlayoutaddress1)

                binding.txLayoutAddress2.isErrorEnabled=true
                binding.txLayoutAddress2.error=resources.getString(R.string.addressLine2Error)
                setBorderColor(binding.txLayoutAddress2)

                binding.txLayoutStreetName.isErrorEnabled=true
                binding.txLayoutStreetName.error=resources.getString(R.string.streetNameError)
                setBorderColor(binding.txLayoutStreetName)

                binding.txlayoutCountry.isErrorEnabled=true
                binding.txlayoutCountry.error=resources.getString(R.string.countryError)
                setBorderColor(binding.txlayoutCountry)

                binding.txlayoutStateData.isErrorEnabled=true
                binding.txlayoutStateData.error=resources.getString(R.string.stateError)
                setBorderColor(binding.txlayoutStateData)

                binding.txLayoutZipCode.isErrorEnabled=true
                binding.txLayoutZipCode.error=resources.getString(R.string.zipCodeError)
                setBorderColor(binding.txLayoutZipCode)
            }
            else if(result.toString().equals(resources.getString(R.string.zipCodeValidation)))
            {
                binding.txLayoutZipCode.isErrorEnabled=true
                binding.txLayoutZipCode.error=resources.getString(R.string.zipCodeValidation)
                setBorderColor(binding.txLayoutZipCode)
            }

        })


//        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
//        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        return binding.root
    }

    private fun setBorderColor(txLayoutdate: TextInputLayout) {
        txLayoutdate.boxStrokeErrorColor = ColorStateList.valueOf(resources.getColor(R.color.red))
        txLayoutdate.boxStrokeWidth = 2
        txLayoutdate.boxStrokeWidthFocused = 2
        txLayoutdate.boxStrokeColor = Color.RED
    }


}