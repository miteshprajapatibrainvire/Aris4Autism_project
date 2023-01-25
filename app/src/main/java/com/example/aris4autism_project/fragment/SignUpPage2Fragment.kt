package com.example.aris4autism_project.fragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.aris4autism_project.MainActivity
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

        val countryList = resources.getStringArray(R.array.countryStr)
        val countryAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1, countryList
        )
        binding.idCountry.setAdapter(countryAdapter)


        val stateList=resources.getStringArray(R.array.stateStr)
        val stateAdapter=ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,stateList
        )
        binding.idState.setAdapter(stateAdapter)

        var callback=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val myFragment = activity?.findViewById<ViewPager2>(R.id.registerViewPager)
                myFragment?.currentItem=0
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        binding.idAddress1.addTextChangedListener(txWatcherAddress1)
        binding.idAddress2.addTextChangedListener(txWatcherAddress2)
        binding.idStreetName.addTextChangedListener(txWatcherStreetName)
        binding.idCountry.addTextChangedListener(txWatcherCountry)
        binding.idState.addTextChangedListener(txWatcherState)
        binding.idZipCode.addTextChangedListener(txWatcherZipCode)


        viewModel.getSignUpAddressResult().observe(requireActivity()) { result ->

            if (result.toString().equals(resources.getString(R.string.emptyData))) {
                binding.txlayoutaddress1.isErrorEnabled = true
                binding.txlayoutaddress1.error = resources.getString(R.string.addressLine1Error)
                setBorderColor(binding.txlayoutaddress1)

                binding.txLayoutAddress2.isErrorEnabled = true
                binding.txLayoutAddress2.error = resources.getString(R.string.addressLine2Error)
                setBorderColor(binding.txLayoutAddress2)

                binding.txLayoutStreetName.isErrorEnabled = true
                binding.txLayoutStreetName.error = resources.getString(R.string.streetNameError)
                setBorderColor(binding.txLayoutStreetName)

                binding.txlayoutCountry.isErrorEnabled = true
                binding.txlayoutCountry.error = resources.getString(R.string.countryError)
                setBorderColor(binding.txlayoutCountry)

                binding.txlayoutStateData.isErrorEnabled = true
                binding.txlayoutStateData.error = resources.getString(R.string.stateError)
                setBorderColor(binding.txlayoutStateData)

                binding.txLayoutZipCode.isErrorEnabled = true
                binding.txLayoutZipCode.error = resources.getString(R.string.zipCodeError)
                setBorderColor(binding.txLayoutZipCode)

            } else if (result.toString().equals(resources.getString(R.string.addressLine1Error))) {
                binding.txlayoutaddress1.isErrorEnabled = true
                binding.txlayoutaddress1.error = resources.getString(R.string.addressLine1Error)
                setBorderColor(binding.txlayoutaddress1)
            } else if (result.toString().equals(resources.getString(R.string.addressLine2Error))) {
                binding.txLayoutAddress2.isErrorEnabled = true
                binding.txLayoutAddress2.error = resources.getString(R.string.addressLine2Error)
                setBorderColor(binding.txLayoutAddress2)
            } else if (result.toString().equals(resources.getString(R.string.streetNameError))) {
                binding.txlayoutStateData.isErrorEnabled = true
                binding.txlayoutStateData.error = resources.getString(R.string.stateError)
                setBorderColor(binding.txlayoutStateData)
            } else if (result.toString().equals(resources.getString(R.string.countryError))) {
                binding.txlayoutCountry.isErrorEnabled = true
                binding.txlayoutCountry.error = resources.getString(R.string.countryError)
                setBorderColor(binding.txlayoutCountry)
            } else if (result.toString().equals(resources.getString(R.string.zipCodeError))) {
                binding.txLayoutZipCode.isErrorEnabled = true
                binding.txLayoutZipCode.error = resources.getString(R.string.zipCodeError)
                setBorderColor(binding.txLayoutZipCode)
            } else if (result.toString().equals(resources.getString(R.string.zipCodeValidation))) {
                binding.txLayoutZipCode.isErrorEnabled = true
                binding.txLayoutZipCode.error = resources.getString(R.string.zipCodeValidation)
                setBorderColor(binding.txLayoutZipCode)
            } else if (result.toString().equals("Valid Credential")) {
                Toast.makeText(requireContext(), result.toString(), Toast.LENGTH_SHORT).show()
            }
        }


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

    private val txWatcherAddress1 = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            binding.txlayoutaddress1.isErrorEnabled = false

            binding.txlayoutaddress1.boxStrokeErrorColor =
                ColorStateList.valueOf(resources.getColor(R.color.gray))
            binding.txlayoutaddress1.boxStrokeWidth = 1
            binding.txlayoutaddress1.boxStrokeWidthFocused = 1
            binding.txlayoutaddress1.boxStrokeColor = Color.GRAY
        }
    }
    private val txWatcherAddress2 = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            binding.txLayoutAddress2.isErrorEnabled = false

            binding.txLayoutAddress2.boxStrokeErrorColor =
                ColorStateList.valueOf(resources.getColor(R.color.gray))
            binding.txLayoutAddress2.boxStrokeWidth = 1
            binding.txLayoutAddress2.boxStrokeWidthFocused = 1
            binding.txLayoutAddress2.boxStrokeColor = Color.GRAY
        }
    }
    private val txWatcherStreetName = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            binding.txLayoutStreetName.isErrorEnabled = false

            binding.txLayoutStreetName.boxStrokeErrorColor =
                ColorStateList.valueOf(resources.getColor(R.color.gray))
            binding.txLayoutStreetName.boxStrokeWidth = 1
            binding.txLayoutStreetName.boxStrokeWidthFocused = 1
            binding.txLayoutStreetName.boxStrokeColor = Color.GRAY
        }
    }

    private val txWatcherCountry = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            binding.txlayoutCountry.isErrorEnabled = false

            binding.txlayoutCountry.boxStrokeErrorColor =
                ColorStateList.valueOf(resources.getColor(R.color.gray))
            binding.txlayoutCountry.boxStrokeWidth = 1
            binding.txlayoutCountry.boxStrokeWidthFocused = 1
            binding.txlayoutCountry.boxStrokeColor = Color.GRAY
        }
    }
    private val txWatcherState = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            binding.txlayoutStateData.isErrorEnabled = false

            binding.txlayoutStateData.boxStrokeErrorColor =
                ColorStateList.valueOf(resources.getColor(R.color.gray))
            binding.txlayoutStateData.boxStrokeWidth = 1
            binding.txlayoutStateData.boxStrokeWidthFocused = 1
            binding.txlayoutStateData.boxStrokeColor = Color.GRAY
        }
    }

    private val txWatcherZipCode = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            binding.txLayoutZipCode.isErrorEnabled = false
            binding.txLayoutZipCode.boxStrokeErrorColor =
                ColorStateList.valueOf(resources.getColor(R.color.gray))
            binding.txLayoutZipCode.boxStrokeWidth = 1
            binding.txLayoutZipCode.boxStrokeWidthFocused = 1
            binding.txLayoutZipCode.boxStrokeColor = Color.GRAY
        }
    }




}