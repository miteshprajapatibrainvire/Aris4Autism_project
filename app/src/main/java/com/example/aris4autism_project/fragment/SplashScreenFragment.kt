package com.example.aris4autism_project.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.databinding.FragmentSplashScreenBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class SplashScreenFragment : Fragment() {

    lateinit var binding: FragmentSplashScreenBinding
    lateinit var bottonNavView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        bottonNavView = activity?.findViewById(R.id.bottom_navigation)!!
        bottonNavView.visibility = View.GONE

        //set splash screen details when user already login or not
        Handler(Looper.getMainLooper()).postDelayed({
            val sharedData =
                requireActivity().getSharedPreferences(Constant.TokenData, Context.MODE_PRIVATE)
            if (null != sharedData.getString(Constant.TokenData, null)) {
                //navigate splashscreen to learnerfragment
                findNavController().navigate(R.id.action_splashScreenFragment_to_learnersFragment2)
            }
            else
            {
                //navigate splashscreen to signinfragment
                findNavController().navigate(R.id.action_splashScreenFragment_to_singInFragment)
            }

        }, 3000)

        return binding.root

    }

}