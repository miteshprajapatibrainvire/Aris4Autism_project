package com.example.aris4autism_project.fragment

import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.ProfileAdapter
import com.example.aris4autism_project.databinding.FragmentSingUpBinding
import com.example.aris4autism_project.model.ProfileModel
import com.example.aris4autism_project.viewmodel.SignUpViewModel
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class SignUpFragment : Fragment() {

    lateinit var binding: FragmentSingUpBinding
    lateinit var viewModel: SignUpViewModel
    lateinit var adpProfile: ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingUpBinding.inflate(layoutInflater, container, false)

        val languages = resources.getStringArray(R.array.genStr)
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1, languages
        )

        binding.spGender.setAdapter(adapter)

        viewModel = ViewModelProvider(requireActivity()).get(SignUpViewModel::class.java)
        binding.signUpModel = viewModel
        binding.lifecycleOwner = this

        var callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        binding.btnSingUp.setOnClickListener {
            findNavController().navigate(R.id.action_singUpFragment_to_mainFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)

        iconsList()

        addTextWatcher()

        callViewModel()

        binding.iddob.setOnClickListener {
            clickDatePicker()
        }

        return binding.root
    }

    private fun callViewModel() {
       viewModel.getSignUpResult().observe(requireActivity()) { result ->

            Log.e("resultData=", result.toString())

            if (result.toString().equals(resources.getString(R.string.fullname))) {

                binding.txLayoutFullName.isErrorEnabled = true
                binding.txLayoutFullName.error = resources.getString(R.string.enterfullaname)
                setBorderColor(binding.txLayoutFullName)

            } else if (result.toString().equals(resources.getString(R.string.mobileno))) {
                binding.txLayoutMobileNumber.error = resources.getString(R.string.mobileNumber)
                binding.txLayoutMobileNumber.isErrorEnabled = true
                setBorderColor(binding.txLayoutMobileNumber)

            } else if (result.toString().equals(resources.getString(R.string.mobileValidation))) {
                binding.txLayoutMobileNumber.error = resources.getString(R.string.mobileValidation)
                binding.txLayoutMobileNumber.isErrorEnabled = true
                setBorderColor(binding.txLayoutMobileNumber)
            } else if (result.toString().equals(resources.getString(R.string.emailadd))) {
                binding.txlayoutEmailData.error = resources.getString(R.string.emailAddress)
                binding.txlayoutEmailData.isErrorEnabled = true
                setBorderColor(binding.txlayoutEmailData)
            } else if (result.toString().equals(resources.getString(R.string.invalidemail))) {
                binding.txlayoutEmailData.error = resources.getString(R.string.invalidEmail)
                binding.txlayoutEmailData.isErrorEnabled = true
                setBorderColor(binding.txlayoutEmailData)
            } else if (result.toString().equals(resources.getString(R.string.selectgen))) {
                binding.txlayoutGender.error = resources.getString(R.string.genderStr)
                binding.txlayoutGender.isErrorEnabled = true
                setBorderColor(binding.txlayoutGender)
            } else if (result.toString().equals(resources.getString(R.string.selectDob))) {
                binding.txLayoutdate.error = resources.getString(R.string.selectDob)
                binding.txLayoutdate.isErrorEnabled = true
                setBorderColor(binding.txLayoutdate)
            } else if (result.toString().equals(resources.getString(R.string.enterpassword))) {
                binding.txlayoutpassword.error = resources.getString(R.string.passwordStr)
                binding.txlayoutpassword.isErrorEnabled = true
                setBorderColor(binding.txlayoutpassword)
            } else if (result.toString().equals(resources.getString(R.string.passconfirm))) {
                binding.txlayoutConfirmpassword.error = resources.getString(R.string.passSame)
                binding.txlayoutConfirmpassword.isErrorEnabled = true
                setBorderColor(binding.txlayoutConfirmpassword)
            }
            else if (result.toString()
                    .equals(resources.getString(R.string.confirmpasswordValidation))
            ) {
                Log.e("1validation=",result.toString())
                Log.e("1validation=",resources.getString(R.string.passwordValidation))
                // Toast.makeText(requireContext(), "validation password", Toast.LENGTH_SHORT).show()
                binding.txlayoutConfirmpassword.error = resources.getString(R.string.confirmpasswordValidation)
                binding.txlayoutConfirmpassword.isErrorEnabled = true
                setBorderColor(binding.txlayoutConfirmpassword)
            }
            else if (result.toString().equals(resources.getString(R.string.passwordenter))) {
                binding.txlayoutConfirmpassword.error =
                    resources.getString(R.string.enter_confirm_passwordd)
                binding.txlayoutConfirmpassword.isErrorEnabled = true
                setBorderColor(binding.txlayoutConfirmpassword)
            } else if (result.toString()
                    .equals(resources.getString(R.string.passwordValidation))
            ) {
//                Log.e("1validation=",result.toString())
//                Log.e("1validation=",resources.getString(R.string.passwordValidation))
                // Toast.makeText(requireContext(), "validation password", Toast.LENGTH_SHORT).show()
                binding.txlayoutpassword.error = resources.getString(R.string.passwordValidation)
                binding.txlayoutpassword.isErrorEnabled = true
                setBorderColor(binding.txlayoutpassword)

            }

            else if (binding.idpassword.text!!.equals(binding.idConfirmpassword.text)) {
                binding.txlayoutConfirmpassword.error =
                    resources.getString(R.string.passwordValidation)
                binding.txlayoutConfirmpassword.isErrorEnabled = true
                setBorderColor(binding.txlayoutConfirmpassword)

            } else if (result.toString().equals(resources.getString(R.string.invalidcredential))) {
                binding.txLayoutFullName.error = resources.getString(R.string.enterfullaname)
                binding.txLayoutFullName.isErrorEnabled = true

                binding.txLayoutMobileNumber.error = resources.getString(R.string.entermobile)
                binding.txLayoutMobileNumber.isErrorEnabled = true

                binding.txlayoutEmailData.error = resources.getString(R.string.emailAddress)
                binding.txlayoutEmailData.isErrorEnabled = true

                binding.txlayoutGender.error = "Please Select Gender Type"
                binding.txlayoutGender.isErrorEnabled = true

                binding.txLayoutdate.error = resources.getString(R.string.selectDob)
                binding.txLayoutdate.isErrorEnabled = true

                binding.txlayoutpassword.error = resources.getString(R.string.enterpass)
                binding.txlayoutpassword.isErrorEnabled = true

                binding.txlayoutConfirmpassword.error = resources.getString(R.string.enterpass)
                binding.txlayoutConfirmpassword.isErrorEnabled = true

            } else if (result.toString().equals("valid registration")) {
                Log.e("result=","valid registration")
                Toast.makeText(requireActivity(), "Registration successfully", Toast.LENGTH_SHORT).show()

                binding.txLayoutFullName.isErrorEnabled = false
                binding.txLayoutMobileNumber.isErrorEnabled = false
                binding.txlayoutEmailData.isErrorEnabled = false
                binding.txlayoutGender.isErrorEnabled = false
                binding.txLayoutdate.isErrorEnabled = false
                binding.txlayoutpassword.isErrorEnabled = false
                binding.txlayoutConfirmpassword.isErrorEnabled = false

            } else if (result.toString().equals(resources.getString(R.string.validcredential))) {
                Toast.makeText(requireActivity(), "Valid Credentials", Toast.LENGTH_SHORT).show()
            }

        }


    }

    private fun setBorderColor(txLayoutdate: TextInputLayout) {
        txLayoutdate.boxStrokeErrorColor = ColorStateList.valueOf(resources.getColor(R.color.red))
        txLayoutdate.boxStrokeWidth = 2
        txLayoutdate.boxStrokeWidthFocused = 2
        txLayoutdate.boxStrokeColor = Color.RED
    }

    private fun addTextWatcher() {
        binding.idFullName.addTextChangedListener(textWatcherFullName)
        binding.idMobileNumber.addTextChangedListener(textWatcherMobileNo)
        binding.idEmailData.addTextChangedListener(textWatcherEmail)
        binding.iddob.addTextChangedListener(textWatcherdob)
        binding.idpassword.addTextChangedListener(textWatcherpassword)
        binding.idConfirmpassword.addTextChangedListener(textWatcherConfirmPass)
        binding.spGender.addTextChangedListener(textWatcherGender)
    }

    private fun iconsList() {

        val arrayProfile = ArrayList<ProfileModel>()
        arrayProfile.add(ProfileModel(R.drawable.profileimg1, false))
        arrayProfile.add(ProfileModel(R.drawable.profile2img, false))
        arrayProfile.add(ProfileModel(R.drawable.profileimg3, false))
        arrayProfile.add(ProfileModel(R.drawable.profileimg4, false))
        arrayProfile.add(ProfileModel(R.drawable.profileimggirl1, false))
        arrayProfile.add(ProfileModel(R.drawable.profilegirlimg2, false))
        arrayProfile.add(ProfileModel(R.drawable.profilegirlimg3, false))
        arrayProfile.add(ProfileModel(R.drawable.profileimggirl4, false))
        adpProfile = ProfileAdapter(arrayProfile)
        binding.recyId.layoutManager = GridLayoutManager(requireActivity(), 4)
        binding.recyId.adapter = adpProfile

    }

    fun TextInputLayout.markRequiredInRed() {
        hint = buildSpannedString {
            append(hint)
            color(Color.RED) { append(" *") }
        }
    }

    fun TextInputLayout.markRequiredInGray(str: String) {
        error = buildSpannedString {
            append(error)
            color(Color.parseColor("#FF0000")) { append(str) }
        }
    }

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

                binding.iddob.setText(strData)
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 8640000
        dpd.show()
    }

    private val textWatcherFullName = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            binding.txLayoutFullName.isErrorEnabled = false
            binding.txLayoutFullName.boxStrokeErrorColor =
                ColorStateList.valueOf(resources.getColor(R.color.gray))
            binding.txLayoutFullName.boxStrokeWidth = 1
            binding.txLayoutFullName.boxStrokeWidthFocused = 1
            binding.txLayoutFullName.boxStrokeColor = Color.GRAY

        }
    }

    private val textWatcherMobileNo = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            binding.txLayoutMobileNumber.isErrorEnabled = false
            binding.txLayoutMobileNumber.boxStrokeErrorColor =
                ColorStateList.valueOf(resources.getColor(R.color.gray))
            binding.txLayoutMobileNumber.boxStrokeWidth = 1
            binding.txLayoutMobileNumber.boxStrokeWidthFocused = 1
            binding.txLayoutMobileNumber.boxStrokeColor = Color.GRAY
        }
    }

    private val textWatcherEmail = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            binding.txlayoutEmailData.isErrorEnabled = false

            binding.txlayoutEmailData.boxStrokeErrorColor =
                ColorStateList.valueOf(resources.getColor(R.color.gray))
            binding.txlayoutEmailData.boxStrokeWidth = 1
            binding.txlayoutEmailData.boxStrokeWidthFocused = 1
            binding.txlayoutEmailData.boxStrokeColor = Color.GRAY
        }
    }

    private val textWatcherGender = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
        override fun afterTextChanged(p0: Editable?) {
            binding.txlayoutGender.isErrorEnabled = false
            binding.txlayoutGender.boxStrokeErrorColor =
                ColorStateList.valueOf(resources.getColor(R.color.gray))
            binding.txlayoutGender.boxStrokeWidth = 1
            binding.txlayoutGender.boxStrokeWidthFocused = 1
            binding.txlayoutGender.boxStrokeColor = Color.GRAY
        }
    }

    private val textWatcherdob = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
        override fun afterTextChanged(p0: Editable?) {
            binding.txLayoutdate.isErrorEnabled = false
            binding.txLayoutdate.boxStrokeErrorColor =
                ColorStateList.valueOf(resources.getColor(R.color.gray))
            binding.txLayoutdate.boxStrokeWidth = 1
            binding.txLayoutdate.boxStrokeWidthFocused = 1
            binding.txLayoutdate.boxStrokeColor = Color.GRAY
        }
    }

    private val textWatcherpassword = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            binding.txlayoutpassword.isErrorEnabled = false

            binding.txlayoutpassword.boxStrokeErrorColor =
                ColorStateList.valueOf(resources.getColor(R.color.gray))
            binding.txlayoutpassword.boxStrokeWidth = 1
            binding.txlayoutpassword.boxStrokeWidthFocused = 1
            binding.txlayoutpassword.boxStrokeColor = Color.GRAY
        }
    }

    private val textWatcherConfirmPass = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            binding.txlayoutConfirmpassword.isErrorEnabled = false

            binding.txlayoutConfirmpassword.boxStrokeErrorColor =
                ColorStateList.valueOf(resources.getColor(R.color.gray))
            binding.txlayoutConfirmpassword.boxStrokeWidth = 1
            binding.txlayoutConfirmpassword.boxStrokeWidthFocused = 1
            binding.txlayoutConfirmpassword.boxStrokeColor = Color.GRAY
        }
    }
}