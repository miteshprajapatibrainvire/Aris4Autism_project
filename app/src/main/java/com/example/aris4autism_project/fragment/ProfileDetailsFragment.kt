package com.example.aris4autism_project.fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.CalenderFormat
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.databinding.FragmentProfileDetailsBinding
import com.example.aris4autism_project.model.UpdateProfileSendData
import com.example.aris4autism_project.model.profilemodel.UserProfileResponseModel
import com.example.aris4autism_project.model.responsemodel.ResponseData
import com.example.aris4autism_project.model.responsemodel.ResponseHandler
import com.example.aris4autism_project.viewmodel.ProfileDetailViewModel
import com.example.aris4autism_project.viewmodel.ProfileDetailViewModelFactory
import com.google.android.material.textfield.TextInputLayout
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class ProfileDetailsFragment : Fragment() {

    lateinit var binding: FragmentProfileDetailsBinding
    private var GenArray = ArrayList<String>()
     var genSelect: String = " "
    lateinit var dobSelect: String
    lateinit var viewModelUpdate: ProfileDetailViewModel
    lateinit var uuid: String
    var dobFormat: String = ""
    var ageCalculate: String = ""

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileDetailsBinding.inflate(inflater)

        val constDialog=Constant.getDialogCustom(requireActivity())

        genSelect="male"

        viewModelUpdate = ViewModelProvider(
            requireActivity(),
            ProfileDetailViewModelFactory(requireActivity())
        ).get(ProfileDetailViewModel::class.java)

        binding.profileData=viewModelUpdate
        binding.lifecycleOwner=this

       // binding.editFullName.addTextChangedListener(textWatcherFullName)

        //call api
        viewModelUpdate.getUserProfileDetails()

        viewModelUpdate.resultProfileValidation.observe(viewLifecycleOwner){

            Log.e("profileValidate=",it.toString())
            if(it.toString().equals("data filled not empty"))
            {
                Log.e("data Filled=","record filled not empty")
                viewModelUpdate.profileUpdateDetail(
                UpdateProfileSendData(
                    uuid,
                    "1",
                    binding.editFullName.text.toString(),
                    binding.editMobileNo.text.toString(),
                    genSelect.lowercase(Locale.getDefault()),
                    dobFormat,
                    ageCalculate
                )
            )
            }
            if(it.toString().equals(resources.getString(R.string.bothempty)))
            {
                binding.fullNameTxInput.error=resources.getString(R.string.fllname)
                binding.fullNameTxInput.isErrorEnabled=true
                setBorderColor(binding.fullNameTxInput)

                binding.mobileNumtxInput.error=resources.getString(R.string.entermobile)
                binding.mobileNumtxInput.isErrorEnabled=true
                setBorderColor(binding.mobileNumtxInput)
            }
            if(it.toString().equals(resources.getString(R.string.fllname)))
            {
                    binding.fullNameTxInput.error=resources.getString(R.string.fllname)
                    binding.fullNameTxInput.isErrorEnabled=true
                    setBorderColor(binding.fullNameTxInput)
            }
            if(it.toString().equals(resources.getString(R.string.entermobile)))
            {
                    binding.mobileNumtxInput.error=resources.getString(R.string.entermobile)
                    binding.mobileNumtxInput.isErrorEnabled=true
                    setBorderColor(binding.mobileNumtxInput)
            }
            if(it.toString().equals(resources.getString(R.string.mNo)))
            {
                binding.mobileNumtxInput.error=resources.getString(R.string.mNo)
                binding.mobileNumtxInput.isErrorEnabled=true
                setBorderColor(binding.mobileNumtxInput)
            }

        }

//        binding.btnSaveProfile.setOnClickListener {

////            Toast.makeText(requireContext(), "responseprofile", Toast.LENGTH_SHORT).show()
//           // Log.e("responseData=","responseProfileUpdate")
//            viewModelUpdate.profileUpdateDetail(
//                UpdateProfileSendData(
//                    uuid,
//                    "1",
//                    binding.editFullName.text.toString(),
//                    binding.editMobileNo.text.toString(),
//                    genSelect.lowercase(Locale.getDefault()),
//                    dobFormat,
//                    ageCalculate
//                )
//            )

//        }

        /*
        viewModelUpdate.resultProfileUpdate.observe(requireActivity()) {
            when (it) {
                is BaseResponse.Success -> {

                    Log.e("response=", it.data!!.meta.message)
                    Toast.makeText(
                        requireActivity(),
                        it.data.meta.message,
                        Toast.LENGTH_SHORT
                    ).show()
                    constDialog.cancel()

                }
                is BaseResponse.Error -> {
                    constDialog.cancel()
                }
                is BaseResponse.Loading -> {
                    constDialog.show()
                }
            }
        }

         */



        //set array in gender adapter
        GenArray.add("Male")
        GenArray.add("Female")
        GenArray.add("Prefer not to say")
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            GenArray
        )

        binding.spProfileGen.setAdapter(adapter)

        //get gender selected string
        binding.spProfileGen.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>,
                arg1: View?,
                position: Int,
                arg3: Long
            ) {
                val item = parent.getItemAtPosition(position)
                genSelect = item.toString()
            }
        })

        //fetch profileuser details from userver

        viewModelUpdate.resultProfileUser.observe(viewLifecycleOwner) { state ->

            when (state) {
                is ResponseHandler.Loading->{

                }
                is ResponseHandler.OnFailed->{

                }
                is ResponseHandler.OnSuccessResponse<ResponseData<UserProfileResponseModel>?>->
                {
                    Log.e("responseProfileData=",state.response!!.data!!.toString())
                    uuid = state.response.data!!.uuid
                    binding.editFullName.setText(state.response.data!!.name)
                    binding.editMobileNo.setText(state.response.data!!.phoneNumber.toString())
                    binding.emailEdit.setText(state.response.data!!.email)
                    binding.dobEd.setText(state.response.data!!.dateOfBirth)
//                    binding.spProfileGen.setText(it.data.data.gender)
                    Log.e("ageCalculate=", ageCalculate)
                    dobFormat = binding.dobEd.text.toString()
                    val parser = SimpleDateFormat("yyyy-MM-dd")
                    val formatter = SimpleDateFormat("dd/MM/yyyy")
                    dobFormat = formatter.format(parser.parse(binding.dobEd.text.toString())!!)
                    ageCalculate = dobToAge(binding.dobEd.text.toString())
                    binding.idTxData.text="Your age is "+dobToAge(binding.dobEd.text.toString())

                      for(i in GenArray.indices)
                      {
                                if(GenArray[i].toLowerCase().equals(state.response.data!!.gender.toLowerCase(),true))
                                {
                                    binding.spProfileGen.setText(binding.spProfileGen.getAdapter().getItem(i).toString(), false)
                                    Log.e("selectItem=",state.response.data!!.gender.toString())
                                    break
                                }
                      }
                }
            }
        }

        /*
        viewModelUpdate.resultProfileUser.observe(requireActivity()) {
            when (it) {
                is BaseResponse.Success -> {
                    Log.e("ProfileDetails=", it.data!!.data.toString())
                    uuid = it.data.data.uuid
                    binding.editFullName.setText(it.data.data.name)
                    binding.editMobileNo.setText(it.data.data.phoneNumber)
                    binding.emailEdit.setText(it.data.data.email)
                    binding.dobEd.setText(it.data.data.dateOfBirth)
//                    binding.spProfileGen.setText(it.data.data.gender)
                    Log.e("ageCalculate=", ageCalculate)
                    dobFormat = binding.dobEd.text.toString()
                    val parser = SimpleDateFormat("yyyy-MM-dd")
                    val formatter = SimpleDateFormat("dd/MM/yyyy")
                    dobFormat = formatter.format(parser.parse(binding.dobEd.text.toString())!!)
                    ageCalculate = dobToAge(binding.dobEd.text.toString())
                    binding.idTxData.text="Your age is "+dobToAge(binding.dobEd.text.toString())
                    constDialog.cancel()
                }
                is BaseResponse.Error -> {
                       constDialog.cancel()
                }
                is BaseResponse.Loading -> {
                        constDialog.show()
                }
            }
        }

         */

        //set validation profiledetails
//        viewModelUpdate.resultProfileValidate.observe(requireActivity()) { result ->
//
//            if (result.toString().equals(R.string.enterfullaname)) {
//                Log.e("result=", result.toString())
//            }
//
//            if (result.toString().equals(R.string.entermobile)) {
//                Log.e("result=", result.toString())
//            }
//
//            if (result.toString().equals("valid Data")) {
//                Log.e("result=", result.toString())
//            }
//        }

        //click for open datepicker dialog
        binding.dobEd.setOnClickListener {
            clickDatePicker()
        }

        return binding.root
    }
    private fun setBorderColor(txLayoutdata: TextInputLayout) {
        txLayoutdata.boxStrokeErrorColor =
            ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.red))
        txLayoutdata.boxStrokeWidth = 2
        txLayoutdata.boxStrokeWidthFocused = 2
        txLayoutdata.boxStrokeColor = Color.RED
    }

    fun dobToAge(dateOfBirth: String): String {
        return if (!Utils.checkDateFormat(dateOfBirth, CalenderFormat.MM_DD_YYYY_D.type)) {
            val formatter: DateFormat =
                SimpleDateFormat(CalenderFormat.YYYY_MM_DD.type, Locale.ROOT)
            val formatter2: DateFormat =
                SimpleDateFormat(CalenderFormat.MM_DD_YYYY_D.type, Locale.ROOT)
            val date = formatter.parse(dateOfBirth) as Date
            val date2 = formatter2.format(date)
            Utils.calculateAge(date2)
        } else {
            Utils.calculateAge(dateOfBirth)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun clickDatePicker() {
        val myCalander = Calendar.getInstance()
        val year = myCalander.get(Calendar.YEAR)
        val month = myCalander.get(Calendar.MONTH)
        val day = myCalander.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { datePicker, y, m, d ->
                val monthData = m + 1
                val strData: String = d.toString() + "/" + monthData.toString() + "/" + y.toString()
                dobSelect = strData
                val parser = SimpleDateFormat("yyyy-MM-dd")
                val formatter = SimpleDateFormat("dd/MM/yyyy")
                dobFormat = formatter.format(parser.parse(binding.dobEd.text.toString())!!)
                //Log.e("calculateAge=",dobToAge(binding.dobEd.text.toString()))
                binding.dobEd.setText(strData)
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 8640000
        dpd.show()
    }

}