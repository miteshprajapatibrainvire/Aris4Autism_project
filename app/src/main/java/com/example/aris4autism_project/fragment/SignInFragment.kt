package com.example.aris4autism_project.fragment

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.ObservableInt
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.aris4autism_project.ChangePasswordActivity
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.FragmentSingInBinding
import com.example.aris4autism_project.viewmodel.SignInViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class SignInFragment : Fragment() {

    private lateinit var navController: NavController
    lateinit var binding: FragmentSingInBinding
    lateinit var viewModel: SignInViewModel
    val contentString = ObservableInt()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSingInBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        binding.signInviewModel = viewModel
        binding.lifecycleOwner = this


        viewModel.apply {

            getLogInResult().observe(viewLifecycleOwner, Observer { result ->

//                Log.e("Str=",resources.getString(R.string.enteryouremail))
//                Log.e("result=",result.toString())

                if (result.toString().equals("valid credention!")) {

                    Toast.makeText(requireContext(), "valid credential", Toast.LENGTH_SHORT).show()

                } else {

                    if (result.toString().equals("email and password empty.")) {

                        binding.txLayoutEmail.hint = "Email ID*"
                        binding.txLayoutPassword.hint = "Password*"
                        binding.txLayoutEmail.error = resources.getString(R.string.enteryouremail)
                        binding.txLayoutPassword.error = resources.getString(R.string.enter_password)

                    } else if (result.toString()
                            .equals("Enter your email")
                    ) {
                        binding.txLayoutEmail.error = resources.getString(R.string.enteryouremail)
                        binding.txLayoutEmail.hint = "Email ID*"
                    }
                    else if(result.toString().equals("Invalid email address!"))
                    {
                        binding.txLayoutEmail.error = resources.getString(R.string.invalidemail)
                        binding.txLayoutEmail.hint = "Email ID*"
                    }
                    else if (result.toString()
                            .equals("Please enter your Password.")
                    ) {

                        binding.txLayoutPassword.error = resources.getString(R.string.enter_password)
                        binding.txLayoutPassword.hint = "Password*"
                        binding.txLayoutEmail.error=null
                        binding.txLayoutEmail.hint="Email Id"

                    } else if (result.toString()
                            .equals("Password should be minimum of 6 characters and must contain at least one number,one special character, and both uppercase and lowercase letters")
                    ) {

                        binding.txLayoutPassword.error = resources.getString(R.string.passwordValidation)
                        binding.txLayoutPassword.hint = "Password*"

                    }

                }
            })
        }


        binding.btnLogin.setOnClickListener {
            if (binding.idEmail.text!!.isEmpty()) {
                binding.txLayoutEmail.helperText = "Email Required*"
                binding.idEmail.requestFocus()
            } else if (binding.idPassword.text!!.isEmpty()) {
                binding.txLayoutPassword.helperText = "Password Required*"
                binding.idPassword.requestFocus()
            } else if (binding.idEmail.text!!.isEmpty() && binding.idPassword.text!!.isEmpty()) {
                binding.txLayoutEmail.helperText = "Email Required*"
                binding.txLayoutPassword.helperText = "Password Required*"
                binding.idEmail.requestFocus()
            } else {

            }
        }

        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        val spannable =
            SpannableString("Don't Have An Account?  Sign Up")

        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor("#1E4884")),
            23,
            31,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val cs: ClickableSpan = object : ClickableSpan() {
            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
                ds.setColor(ds.linkColor);
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
}