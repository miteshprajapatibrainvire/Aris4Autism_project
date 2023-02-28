package com.example.aris4autism_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aris4autism_project.databinding.FragmentChangePasswordeBinding

class ChangePasswordeFragment : Fragment() {


    lateinit var binding: FragmentChangePasswordeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangePasswordeBinding.inflate(inflater)


        return binding.root
    }


}