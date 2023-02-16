package com.example.aris4autism_project.fragment

import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.aris4autism_project.IOnBackPressed
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.ProfileAdapter
import com.example.aris4autism_project.databinding.FragmentSignUpPage1Binding
import com.example.aris4autism_project.model.ProfileModel
import com.example.aris4autism_project.viewmodel.SignUpModelFactory
import com.example.aris4autism_project.viewmodel.SignUpViewModel
import com.google.android.material.textfield.TextInputLayout
import java.util.*


class SignUpPage1Fragment : Fragment(), IOnBackPressed {

    lateinit var binding: FragmentSignUpPage1Binding
    lateinit var adpProfile: ProfileAdapter
    lateinit var viewModel: SignUpViewModel
     var genSelect:String = ""
     var dobSelect:String = ""
     var fullName:String = ""
     var mobileNo:String = ""
     var email:String = ""
     var password:String = ""
    val GenArray=ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpPage1Binding.inflate(layoutInflater, container, false)

        GenArray.add("Male")
        GenArray.add("Female")
        GenArray.add("Prefer not to say")
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1, GenArray
        )

        binding.spGender.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, arg1: View?, position: Int, arg3: Long) {

                val item = parent.getItemAtPosition(position)
                genSelect=item.toString()

            }
        })

        binding.spGender.setAdapter(adapter)
        viewModel=ViewModelProvider(requireActivity(), SignUpModelFactory(requireActivity())).get(SignUpViewModel::class.java)
        binding.signUpModel = viewModel
        binding.lifecycleOwner = this

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_singUpFragment_to_singInFragment)
            }
        }

        //change spannable color

        val spannable =
            SpannableString("Choose Profile icon*")

        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
           19,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor("#54606C")),
            0,
            19,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor("#FF0000")),
            19,
            20,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )


        binding.mtxchooseIcon.setMovementMethod(LinkMovementMethod.getInstance())
        binding.mtxchooseIcon.setText(spannable)

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(requireActivity(), object : OnBackPressedCallback(true) {

                override fun handleOnBackPressed() {

                    if (isEnabled) {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }

                }
            })

        binding.btnSingUp.setOnClickListener {
            findNavController().navigate(R.id.action_singUpFragment_to_singInFragment)
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

    private fun clickDatePicker()
    {
        val myCalander = Calendar.getInstance()
        val year = myCalander.get(Calendar.YEAR)
        val month = myCalander.get(Calendar.MONTH)
        val day = myCalander.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { datePicker, y, m, d ->
                val monthData = m + 1
                val strData: String = d.toString() + "/" + monthData.toString() + "/" + y.toString()
                dobSelect= strData
                binding.iddob.setText(strData)
            },
            year,
            month,
            day)

        dpd.datePicker.maxDate = System.currentTimeMillis() - 8640000
        dpd.show()

    }

    private fun callViewModel() {
        viewModel.getSignUpResult().observe(requireActivity()) { result ->

            if (result.toString().equals(resources.getString(R.string.fullname))) {
                binding.txLayoutFullName.isErrorEnabled = true
                binding.txLayoutFullName.error = resources.getString(R.string.enterfullaname)
                setBorderColor(binding.txLayoutFullName)
            }
            else if (result.toString().equals(resources.getString(R.string.mobileno))) {
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
            } else if (result.toString().equals(resources.getString(R.string.dobSelect))) {
                binding.txLayoutdate.error = resources.getString(R.string.selectDob)
                binding.txLayoutdate.isErrorEnabled = true
                setBorderColor(binding.txLayoutdate)
            } else if (result.toString().equals(resources.getString(R.string.enterpassword))) {
                binding.txlayoutpassword.error = resources.getString(R.string.passwordStr)
                binding.txlayoutpassword.isErrorEnabled = true
                setBorderColor(binding.txlayoutpassword)
            }
            else if (result.toString().equals(resources.getString(R.string.passSame)))
            {
                binding.txlayoutConfirmpassword.error =
                    resources.getString(R.string.passSame)
                binding.txlayoutConfirmpassword.isErrorEnabled = true
                setBorderColor(binding.txlayoutConfirmpassword)
            }
            else if (result.toString().equals(resources.getString(R.string.passconfirm)))
            {
                binding.txlayoutConfirmpassword.error = resources.getString(R.string.enter_password)
                binding.txlayoutConfirmpassword.isErrorEnabled = true
                setBorderColor(binding.txlayoutConfirmpassword)
            }
            else if (result.toString()
                    .equals(resources.getString(R.string.confirmpasswordValidation))
            ) {
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
                binding.txlayoutpassword.error = resources.getString(R.string.passwordValidation)
                binding.txlayoutpassword.isErrorEnabled = true
                setBorderColor(binding.txlayoutpassword)
            }
            else if (result.toString().equals(resources.getString(R.string.invalidcredential))) {
                binding.txLayoutFullName.error = resources.getString(R.string.enterfullaname)
                binding.txLayoutFullName.isErrorEnabled = true
                setBorderColor(binding.txLayoutFullName)

                binding.txLayoutMobileNumber.error = resources.getString(R.string.entermobile)
                binding.txLayoutMobileNumber.isErrorEnabled = true
                setBorderColor(binding.txLayoutMobileNumber)

                binding.txlayoutEmailData.error = resources.getString(R.string.emailAddress)
                binding.txlayoutEmailData.isErrorEnabled = true
                setBorderColor(binding.txlayoutEmailData)

                binding.txlayoutGender.error = resources.getString(R.string.genderType)
                binding.txlayoutGender.isErrorEnabled = true
                setBorderColor(binding.txlayoutGender)

                binding.txLayoutdate.error = resources.getString(R.string.selectDob)
                binding.txLayoutdate.isErrorEnabled = true
                setBorderColor(binding.txLayoutdate)

                binding.txlayoutpassword.error = resources.getString(R.string.enterpass)
                binding.txlayoutpassword.isErrorEnabled = true
                setBorderColor(binding.txlayoutpassword)

                binding.txlayoutConfirmpassword.error = resources.getString(R.string.enterpass)
                binding.txlayoutConfirmpassword.isErrorEnabled = true
                setBorderColor(binding.txlayoutConfirmpassword)
            } else if (result.toString().equals("valid registration")) {

                Log.e("result=","valid registration")
                binding.txLayoutFullName.isErrorEnabled = false
                binding.txLayoutMobileNumber.isErrorEnabled = false
                binding.txlayoutEmailData.isErrorEnabled = false
                binding.txlayoutGender.isErrorEnabled = false
                binding.txLayoutdate.isErrorEnabled = false
                binding.txlayoutpassword.isErrorEnabled = false
                binding.txlayoutConfirmpassword.isErrorEnabled = false

                fullName=binding.idFullName.text.toString()
                email=binding.idEmailData.text.toString()
                mobileNo=binding.idMobileNumber.text.toString()
                password=binding.idpassword.text.toString()

                passFragmentData(fullName,mobileNo,email,genSelect,dobSelect,password)

                val myFragment = activity?.findViewById<ViewPager2>(R.id.registerViewPager)
                myFragment?.currentItem=1

            } else if (result.toString().equals(resources.getString(R.string.validRegistration))) {

                Toast.makeText(requireActivity(), "Valid Credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun passFragmentData(fullname:String,mobileNo:String,email:String,gen:String,dob:String,pass:String)
    {
        SignUpPage2Fragment().also {
            it.getData(fullname,mobileNo,email,gen,dob,pass)
        }
    }



    private fun setBorderColor(txLayoutdata: TextInputLayout)
    {
        txLayoutdata.boxStrokeErrorColor = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.red))
        txLayoutdata.boxStrokeWidth = 2
        txLayoutdata.boxStrokeWidthFocused = 2
        txLayoutdata.boxStrokeColor = Color.RED
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

    private val textWatcherFullName = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            binding.txLayoutFullName.isErrorEnabled = false
            binding.txLayoutFullName.boxStrokeErrorColor = ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.gray))
            binding.txLayoutFullName.boxStrokeWidth = 1
            binding.txLayoutFullName.boxStrokeWidthFocused = 1
            binding.txLayoutFullName.boxStrokeColor = Color.parseColor("#1E4884")

        }
    }

    private val textWatcherMobileNo = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.txLayoutMobileNumber.isErrorEnabled = false
            binding.txLayoutMobileNumber.boxStrokeErrorColor =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.gray))
            binding.txLayoutMobileNumber.boxStrokeWidth = 1
            binding.txLayoutMobileNumber.boxStrokeWidthFocused = 1
            binding.txLayoutMobileNumber.boxStrokeColor =  Color.parseColor("#1E4884")
        }

        override fun afterTextChanged(p0: Editable?) {

        }
    }

    private val textWatcherEmail = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.txlayoutEmailData.isErrorEnabled = false
            binding.txlayoutEmailData.boxStrokeErrorColor =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.gray))
            binding.txlayoutEmailData.boxStrokeWidth = 1
            binding.txlayoutEmailData.boxStrokeWidthFocused = 1
            binding.txlayoutEmailData.boxStrokeColor =  Color.parseColor("#1E4884")
        }

        override fun afterTextChanged(p0: Editable?) {

        }
    }

    private val textWatcherGender = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.txlayoutGender.isErrorEnabled = false
            binding.txlayoutGender.boxStrokeErrorColor =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.gray))
            binding.txlayoutGender.boxStrokeWidth = 1
            binding.txlayoutGender.boxStrokeWidthFocused = 1
            binding.txlayoutGender.boxStrokeColor =  Color.parseColor("#1E4884")
        }
        override fun afterTextChanged(p0: Editable?) {

        }
    }

    private val textWatcherdob = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.txLayoutdate.isErrorEnabled = false
            binding.txLayoutdate.boxStrokeErrorColor =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.gray))
            binding.txLayoutdate.boxStrokeWidth = 1
            binding.txLayoutdate.boxStrokeWidthFocused = 1
            binding.txLayoutdate.boxStrokeColor =  Color.parseColor("#1E4884")
        }
        override fun afterTextChanged(p0: Editable?) {

        }
    }

    private val textWatcherpassword = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.txlayoutpassword.isErrorEnabled = false
            binding.txlayoutpassword.boxStrokeErrorColor =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.gray))
            binding.txlayoutpassword.boxStrokeWidth = 1
            binding.txlayoutpassword.boxStrokeWidthFocused = 1
            binding.txlayoutpassword.boxStrokeColor =  Color.parseColor("#1E4884")
        }

        override fun afterTextChanged(p0: Editable?) {

        }
    }


    private val textWatcherConfirmPass = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            binding.txlayoutConfirmpassword.isErrorEnabled = false
            binding.txlayoutConfirmpassword.boxStrokeErrorColor =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(),R.color.gray))
            binding.txlayoutConfirmpassword.boxStrokeWidth = 1
            binding.txlayoutConfirmpassword.boxStrokeWidthFocused = 1
            binding.txlayoutConfirmpassword.boxStrokeColor =  Color.parseColor("#1E4884")

        }

        override fun afterTextChanged(p0: Editable?) {

        }
    }

    override fun onBackPressed(): Boolean {
        return true
    }


}