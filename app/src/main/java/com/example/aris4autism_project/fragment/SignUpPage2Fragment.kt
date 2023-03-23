package com.example.aris4autism_project.fragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.FragmentSignUpPage2Binding
import com.example.aris4autism_project.model.countrymodel.CountryResponseModel
import com.example.aris4autism_project.model.statemodel.StateResponseModel
import com.example.aris4autism_project.model.authmodel.RequestRegistration
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.model.authmodel.ResponseRegistration
import com.example.aris4autism_project.viewmodel.SignUpModelFactory
import com.example.aris4autism_project.viewmodel.SignUpViewModel
import com.google.android.material.textfield.TextInputLayout

class SignUpPage2Fragment : Fragment() {

    lateinit var binding: FragmentSignUpPage2Binding
    lateinit var viewModel: SignUpViewModel
    var resultBol: Boolean = true

    companion object {
        lateinit var fullname: String
        lateinit var mobileNo: String
        lateinit var email: String
        lateinit var gender: String
        lateinit var dob: String
        lateinit var password: String
        lateinit var addressline1: String
        lateinit var addressline2: String
        lateinit var streetName: String
        lateinit var zipcode: String
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpPage2Binding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity(), SignUpModelFactory(requireActivity())).get(
            SignUpViewModel::class.java
        )

        //attach viewmodel with layout
        binding.signUpModel = viewModel
        //attach viewmodel lifecycler with layout and activity
        binding.lifecycleOwner = this

        val countryList = ArrayList<String>()
        val hashMapCountry = HashMap<Int, String>()

        //get country details
        viewModel.getCountryDetails()

        //set spannable string
        val spannable =
            SpannableString(resources.getString(R.string.privacyData))
        //click on spannable spring for perform event
        val clickSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                Toast.makeText(requireActivity(), getString(R.string.privacyStr), Toast.LENGTH_SHORT).show()
            }
        }

        spannable.setSpan(clickSpan, 15, 46, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        //set spannable color
        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor(resources.getString(R.string.lightblueborder))),
            15,
            46,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        //set spannable linkemovement method and text color and text style
        binding.idPrivacy.setText(spannable, TextView.BufferType.SPANNABLE)
        binding.idPrivacy.setMovementMethod(LinkMovementMethod.getInstance())

        //fetch api country detail
        viewModel.resoutCountry.observe(viewLifecycleOwner,{state->
            when(state)
            {
                is ResponseHandler.Loading->{

                }
                is ResponseHandler.OnFailed->{

                }
                is ResponseHandler.OnSuccessResponse<ResponseData<List<CountryResponseModel>>?>->{
                    Log.e("responseCountryList=", state.response?.data.toString())
                    val ArrayCountry = state.response!!.data
//
                    for (i in state.response.data!!.indices) {
                        countryList.add(ArrayCountry?.get(i)!!.name)
                        hashMapCountry.put(ArrayCountry.get(i).id, ArrayCountry.get(i).name)
                    }

                    val countryAdapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_list_item_1, countryList
                    )
                    binding.idCountry.setAdapter(countryAdapter)
                }
            }
        })

        //set country on click listener
        binding.idCountry.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>,
                arg1: View?,
                position: Int,
                arg3: Long
            ) {
                val item = parent.getItemAtPosition(position)
                val conSelect = item.toString()
                for (i in hashMapCountry) {
                    if (i.value.equals(conSelect))
                    {
                        Log.e("id=", i.key.toString())

                    }
                }
            }
        })

        //call status api
        viewModel.getStatusDetails()

        val stateList = ArrayList<String>()

        //fetch status api data
        viewModel.resultStatus.observe(viewLifecycleOwner,{state->
            when(state)
            {
                is ResponseHandler.Loading->{

                }
                is ResponseHandler.OnFailed->{

                }
                is ResponseHandler.OnSuccessResponse<ResponseData<List<StateResponseModel>>?>->{
                    Log.e("responseState=", state.response?.data.toString())
                    val ArrayCountry = state.response!!.data
                    for (i in state.response.data!!.indices) {
                        stateList.add(ArrayCountry?.get(i)!!.name)
                    }

                    val stateAdapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_list_item_1, stateList
                    )
                    binding.idState.setAdapter(stateAdapter)
                }

            }
        })


        //when user press back button it will move first viewpager position code
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val myFragment = activity?.findViewById<ViewPager2>(R.id.registerViewPager)
                myFragment?.currentItem = 0
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        //set textwatcher listener details
        binding.idAddress1.addTextChangedListener(txWatcherAddress1)
        binding.idAddress2.addTextChangedListener(txWatcherAddress2)
        binding.idStreetName.addTextChangedListener(txWatcherStreetName)
        binding.idCountry.addTextChangedListener(txWatcherCountry)
        binding.idState.addTextChangedListener(txWatcherState)
        binding.idZipCode.addTextChangedListener(txWatcherZipCode)

        //fetch registration login results
        viewModel.resultRegistration.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseHandler.Loading -> {
                    showLoading()
                }
                is ResponseHandler.OnFailed -> {
                    stopLoading()
                }
                is ResponseHandler.OnSuccessResponse<ResponseData<ResponseRegistration>?> -> {
                    stopLoading()
                    findNavController().navigate(R.id.action_singUpFragment_to_singInFragment)
                }
                else -> {

                }
            }
        }

        //set address result details validation
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
                resultBol = false

            } else if (result.toString().equals(resources.getString(R.string.addressLine1Error))) {
                binding.txlayoutaddress1.isErrorEnabled = true
                binding.txlayoutaddress1.error = resources.getString(R.string.addressLine1Error)
                setBorderColor(binding.txlayoutaddress1)
                resultBol = false

            } else if (result.toString().equals(resources.getString(R.string.addressLine2Error))) {
                binding.txLayoutAddress2.isErrorEnabled = true
                binding.txLayoutAddress2.error = resources.getString(R.string.addressLine2Error)
                setBorderColor(binding.txLayoutAddress2)
                resultBol = false
            } else if (result.toString().equals(resources.getString(R.string.streetNameError))) {
                binding.txLayoutStreetName.isErrorEnabled = true
                binding.txLayoutStreetName.error = resources.getString(R.string.streetNameError)
                setBorderColor(binding.txLayoutStreetName)
                resultBol = false
            } else if (result.toString().equals(resources.getString(R.string.stateError))) {
                binding.txlayoutStateData.isErrorEnabled = true
                binding.txlayoutStateData.error = resources.getString(R.string.stateError)
                setBorderColor(binding.txlayoutStateData)
                resultBol = false
            } else if (result.toString().equals(resources.getString(R.string.countryError))) {
                binding.txlayoutCountry.isErrorEnabled = true
                binding.txlayoutCountry.error = resources.getString(R.string.countryError)
                setBorderColor(binding.txlayoutCountry)
                resultBol = false
            } else if (result.toString().equals(resources.getString(R.string.zipCodeError))) {
                binding.txLayoutZipCode.isErrorEnabled = true
                binding.txLayoutZipCode.error = resources.getString(R.string.zipCodeError)
                setBorderColor(binding.txLayoutZipCode)
                resultBol = false
            } else if (result.toString().equals(resources.getString(R.string.zipCodeValidation))) {
                binding.txLayoutZipCode.isErrorEnabled = true
                binding.txLayoutZipCode.error = resources.getString(R.string.zipCodeValidation)
                setBorderColor(binding.txLayoutZipCode)
                resultBol = false
            } else if (result.toString().equals(resources.getString(R.string.validCredit))) {
                resultBol = true
                addressline1 = binding.idAddress1.text.toString()
                addressline2 = binding.idAddress2.text.toString()
                streetName = binding.idStreetName.text.toString()
                zipcode = binding.idZipCode.text.toString()

                val requestModel = RequestRegistration(
                    email, password, mobileNo, fullname,
                    gender.lowercase(), "101", "2", dob, addressline1, addressline2,
                    streetName, zipcode, "1", "ascscdscdscds1111111", "iOS"
                )

                if (resultBol == true) {
                    viewModel.sendRegisterResponse(requestModel)
                }

            }
        }

        return binding.root
    }

    fun getData(
        fname: String,
        mobNo: String,
        eml: String,
        gen: String,
        dobData: String,
        pass: String
    ) {
        fullname = fname
        mobileNo = mobNo
        email = eml
        gender = gen
        dob = dobData
        password = pass
        // Log.e("==passdata==",fullname.toString()+"="+mobileNo.toString()+"="+email.toString()+"="+gender.toString()+"="+dob.toString()+"="+password.toString())

    }

    private fun setBorderColor(txLayoutdate: TextInputLayout) {
        txLayoutdate.boxStrokeErrorColor =
            ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.red))
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
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray))
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
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray))
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
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray))
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
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray))
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
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray))
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
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray))
            binding.txLayoutZipCode.boxStrokeWidth = 1
            binding.txLayoutZipCode.boxStrokeWidthFocused = 1
            binding.txLayoutZipCode.boxStrokeColor = Color.GRAY
        }
    }

    fun showLoading() {
        binding.prgbarLogin.visibility = View.VISIBLE
    }

    fun stopLoading() {
        binding.prgbarLogin.visibility = View.GONE
    }


}