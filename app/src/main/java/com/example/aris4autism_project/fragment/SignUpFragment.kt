package com.example.aris4autism_project.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.MainAdapter
import com.example.aris4autism_project.databinding.FragmentSingUpBinding
import nl.isaac.android.StepIndicator


class SignUpFragment : Fragment() {

    lateinit var binding: FragmentSingUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSingUpBinding.inflate(layoutInflater, container, false)


        //for back  signup to sign in fragment
        binding.layoutId.txSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_singUpFragment_to_singInFragment)
        }

        //for back signup to sign in fragment
        binding.layoutId.imgHeart.setOnClickListener {
            findNavController().navigate(R.id.action_singUpFragment_to_singInFragment)
        }

        //set viewpager adapter for swap signuppage1 and signuppage2
        val viewAdapter= MainAdapter(activity)
        viewAdapter.addFragment(SignUpPage1Fragment(),"")
        viewAdapter.addFragment(SignUpPage2Fragment(),"f")

        binding.registerViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL)
        binding.registerViewPager.adapter=viewAdapter

        binding.registerViewPager.setUserInputEnabled(false)

        val stepIndicatorIcons: StepIndicator = binding.layoutId.stepIndicatorNumbers

        //set stepperchecked number indicator with color,label,text
        stepIndicatorIcons.apply {

            setupWithViewPager(binding.registerViewPager)
            showLabels = true
            labels = listOf("Profile Details", "Address Details")
            fillNextStep = false
            fillPreviousStep = true
            activeStepColor=ContextCompat.getColor(context,R.color.darkblue)
            previousStepColor = ContextCompat.getColor(context, R.color.darkblue)
            activeStepColor = ContextCompat.getColor(context, R.color.darkblue)
            previousStepLabelColor = ContextCompat.getColor(context, R.color.darkblue)
            activeStepLabelColor = ContextCompat.getColor(context, R.color.darkblue)
            activeStepIndicatorTypeColor = ContextCompat.getColor(context, R.color.darkblue)
            previousStepIndicatorTypeColor = ContextCompat.getColor(context, R.color.white)
            nextStepIndicatorTypeColor = ContextCompat.getColor(context, R.color.darkblue)

        }

        return binding.root
    }


}