package com.example.aris4autism_project.fragment

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.Toast
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.FragmentChangePasswordeBinding

class ChangePasswordeFragment : Fragment() {

    var passwordVisible: Boolean = false

    lateinit var binding: FragmentChangePasswordeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangePasswordeBinding.inflate(inflater)



        return binding.root
    }


}