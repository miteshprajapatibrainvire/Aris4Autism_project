package com.example.aris4autism_project.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.Constant

class SplashScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_splash_screen, container, false)

        Handler().postDelayed({
            var sharedData=requireActivity().getSharedPreferences(Constant.TokenData, Context.MODE_PRIVATE)
            var token=sharedData.getString(Constant.TokenData,null)
            if(token!=null)
            {
                findNavController().navigate(R.id.action_splashScreenFragment_to_mainFragment)
            }
            else {
                findNavController().navigate(R.id.action_splashScreenFragment_to_singInFragment)
            }
          },3000)

        return view
    }

}