package com.example.aris4autism_project.stepperdemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.FragmentStep3Binding

class step3Fragment : Fragment() {

    lateinit var binding:FragmentStep3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentStep3Binding.inflate(inflater)
        if(arguments?.getString("usernameStep3")!=null)
        {
            var username:String=arguments?.getString("usernameStep3").toString()
            binding.step3txt.text=username
            Log.e("step2Fragment=",username.toString())
        }

        return binding.root
    }
}