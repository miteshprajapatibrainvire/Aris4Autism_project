package com.example.aris4autism_project.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.aris4autism_project.ChangePasswordActivity
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.Utils.PrefKey
import com.example.aris4autism_project.api.MyPreference
import com.example.aris4autism_project.databinding.FragmentSingInBinding
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.model.login.LoginModel
import com.example.aris4autism_project.viewmodel.SignInViewModel
import com.example.aris4autism_project.viewmodel.SignInViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignInFragment : Fragment() {

    private lateinit var navController: NavController
    lateinit var binding: FragmentSingInBinding
    lateinit var viewModel: SignInViewModel

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSingInBinding.inflate(layoutInflater, container, false)

        viewModel =
            ViewModelProvider(requireActivity(), SignInViewModelFactory(requireActivity())).get(
                SignInViewModel::class.java
            )

        binding.signInviewModel = viewModel

        binding.lifecycleOwner = this
        val constDialog=Constant.getDialogCustom(requireActivity())

        val navHostFragmentData =
            activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragmentData.navController

        //finish activity when user press back button
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
        viewModel.resultLogin=MutableLiveData<ResponseHandler<ResponseData<LoginModel>?>>()
        viewModel.sendLoginResponse(binding.idEmailData.text.toString(),binding.idPassword.text.toString())

        //set textwatcher for user typing change borderbox
        binding.idEmailData.addTextChangedListener(textWatcherEmail)
        binding.idPassword.addTextChangedListener(textWatcherPassword)

        viewModel.resultLogin.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseHandler.Loading -> {
                    constDialog.show()
                }
                is ResponseHandler.OnFailed -> {
                    constDialog.cancel()
                }
                is ResponseHandler.OnSuccessResponse<ResponseData<LoginModel>?> ->
                {
                    Log.e("responseData=", state.response!!.data!!.toString())
                    constDialog.cancel()
                    if (!MyPreference.getValueBoolean(PrefKey.ISLOGIN, true))
                    {
                        MyPreference.setValueBoolean(PrefKey.ISLOGIN, true)
                        MyPreference.setValueString(
                            PrefKey.ACCESS_TOKEN,
                            state.response.data!!.accessToken
                        )
                        findNavController().navigate(R.id.learnersFragment2)

                    }
                }
            }
        }

        //get api response from server
//        viewModel.resultLogin.observe(viewLifecycleOwner) {
//            when (it) {
//
//                is BaseResponse.Success -> {
//                    val editor: SharedPreferences.Editor = sharedData.edit()
//                    editor.putString(Constant.TokenData, it.data!!.data.accessToken)
//                    if (editor.commit()) {
//                        binding.idEmailData.text = null
//                        binding.idPassword.text = null
//
//                        findNavController().navigate(R.id.learnersFragment2)
//
//                        onDestroyView()
//                    }
//                    stopLoading()
////                    viewModel.resultLogin.value = null
//                }
//
//                is BaseResponse.Loading -> {
//                    showLoading()
//                }
//
//                is BaseResponse.Error -> {
//                    stopLoading()
//                    Toast.makeText(requireContext(), it.msg.toString(), Toast.LENGTH_SHORT).show()
//                }
//                else -> {
//
//                }
//            }
//
//        }

        viewModel.apply {

            getLogInResult().observe(viewLifecycleOwner, { result ->

                if (result.toString().equals(resources.getString(R.string.validlogin))) {
                    viewModel.sendLoginResponse(
                        binding.idEmailData.text.toString(),
                        binding.idPassword.text.toString()
                    )
                } else {
                    if (result.toString()
                            .equals(resources.getString(R.string.emailpassempty))
                    ) {
                        binding.txLayoutEmail.hint = resources.getString(R.string.emailidstr)
                        binding.txLayoutPassword.hint = resources.getString(R.string.passStr)
                        binding.txLayoutEmail.error =
                            resources.getString(R.string.enteryouremail)
                        binding.txLayoutPassword.error =
                            resources.getString(R.string.enter_password)
                    } else if (result.toString()
                            .equals(resources.getString(R.string.emaildata))
                    ) {
                        binding.txLayoutEmail.error =
                            resources.getString(R.string.enteryouremail)
                        binding.txLayoutEmail.hint = resources.getString(R.string.emailidstr)
                    } else if (result.toString()
                            .equals(resources.getString(R.string.invalidEmailData))
                    ) {
                        binding.txLayoutEmail.error = resources.getString(R.string.invalidemail)
                        binding.txLayoutEmail.hint = resources.getString(R.string.emailidstr)
                    } else if (result.toString()
                            .equals(resources.getString(R.string.passwordStr))
                    ) {
                        binding.txLayoutPassword.error =
                            resources.getString(R.string.enter_password)
                        binding.txLayoutPassword.hint = resources.getString(R.string.passData)
                        binding.txLayoutEmail.error = null
                        binding.txLayoutEmail.hint = resources.getString(R.string.emailidstr)
                    } else if (result.toString()
                            .equals(resources.getString(R.string.passwordValidation))
                    ) {
                        binding.txLayoutPassword.error =
                            resources.getString(R.string.passwordValidation)
                        binding.txLayoutPassword.hint = resources.getString(R.string.passData)
                    }

                }
            })

        }

        binding.btnLogin.setOnClickListener {
            if (binding.idEmailData.text!!.isEmpty()) {
                binding.txLayoutEmail.helperText = resources.getString(R.string.emailrequired)
                binding.idEmailData.requestFocus()
            } else if (binding.idPassword.text!!.isEmpty()) {
                binding.txLayoutPassword.helperText = resources.getString(R.string.passwordrequired)
                binding.idPassword.requestFocus()
            } else if (binding.idEmailData.text!!.isEmpty() && binding.idPassword.text!!.isEmpty()) {
                binding.txLayoutEmail.helperText = resources.getString(R.string.emailrequired)
                binding.txLayoutPassword.helperText = resources.getString(R.string.passwordrequired)
                binding.idEmailData.requestFocus()
            }
        }

        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        val spannable =
            SpannableString("Don't Have An Account?  Sign Up")

        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            23,
            31,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor("#1E4884")),
            23,
            31,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )


        val cs: ClickableSpan = object : ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
                ds.setColor(ds.linkColor)
//                navController.navigate(R.id.action_singInFragment_to_singUpFragment2)
            }

            override fun onClick(v: View) {
                Log.d("main", "textview clicked")
                findNavController().navigate(R.id.action_singInFragment_to_singUpFragment2)
            }
        }

        spannable.setSpan(cs, 23, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor("#1E4884")),
            23,
            31,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            23,
            31,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.txSingup.setMovementMethod(LinkMovementMethod.getInstance())

        binding.txSingup.setText(spannable)

        binding.txForgotPassword.setOnClickListener {

            val dialog = BottomSheetDialog(requireActivity())
            val view = layoutInflater.inflate(R.layout.forgotpassword_bottomsheet, null)
            val btnClose = view.findViewById<ImageView>(R.id.idClose)
            val emailBox = view.findViewById<TextInputEditText>(R.id.idEmailBottom)
            val layoutEmail = view.findViewById<TextInputLayout>(R.id.txBottomLayoutEmail)
            val btnSend = view.findViewById<MaterialButton>(R.id.idSendBtn)
            emailBox.setOnFocusChangeListener(object : View.OnFocusChangeListener {
                override fun onFocusChange(v: View?, hasFocus: Boolean) {

                    if (hasFocus) {
                        layoutEmail.setHint("Email Id")
                    } else {
                        layoutEmail.setHint("Enter your email")
                    }
                }
            })

            btnSend.setOnClickListener {
                startActivity(Intent(requireActivity(), ChangePasswordActivity::class.java))
            }

            btnClose.setOnClickListener {
                dialog.dismiss()
            }

            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.show()
        }

        return binding.root
    }


     val textWatcherEmail = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.txLayoutEmail.isErrorEnabled = false
        }
    }


     val textWatcherPassword = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.txLayoutPassword.isErrorEnabled = false
        }
    }


}