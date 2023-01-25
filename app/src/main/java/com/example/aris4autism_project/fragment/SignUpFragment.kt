package com.example.aris4autism_project.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.MainAdapter
import com.example.aris4autism_project.adapter.ProfileAdapter
import com.example.aris4autism_project.databinding.FragmentSingUpBinding
import com.example.aris4autism_project.viewmodel.SignUpViewModel
import nl.isaac.android.StepIndicator
import java.util.*


class SignUpFragment : Fragment() {

    lateinit var binding: FragmentSingUpBinding
    lateinit var viewModel: SignUpViewModel
    lateinit var adpProfile: ProfileAdapter
    lateinit var viewPager:ViewPager2

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

        val viewAdapter= MainAdapter(activity)
        viewAdapter.addFragment(SignUpPage1Fragment(),"")
        viewAdapter.addFragment(SignUpPage2Fragment(),"f")

        binding.registerViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL)
        binding.registerViewPager.adapter=viewAdapter

        binding.registerViewPager.setUserInputEnabled(false)

//        val stepIndicatorNumbers: StepIndicator = binding.stepIndicatorNumbers
//        stepIndicatorNumbers.setupWithViewPager(binding.registerViewPager)

        val stepIndicatorIcons: StepIndicator = binding.layoutId.stepIndicatorNumbers

        stepIndicatorIcons.apply {

            setupWithViewPager(binding.registerViewPager)
            showLabels = true
            labels = listOf("Profile Details", "Address Details")
            fillNextStep = false
            fillPreviousStep = true
            previousStepColor = ContextCompat.getColor(context, R.color.darkblue)
            activeStepColor = ContextCompat.getColor(context, R.color.darkblue)
            previousStepLabelColor = ContextCompat.getColor(context, R.color.darkblue)
            activeStepLabelColor = ContextCompat.getColor(context, R.color.darkblue)
            activeStepIndicatorTypeColor = ContextCompat.getColor(context, R.color.darkblue)
            previousStepIndicatorTypeColor = ContextCompat.getColor(context, R.color.white)
            nextStepIndicatorTypeColor = ContextCompat.getColor(context, R.color.darkblue)

        }

        viewModel = ViewModelProvider(requireActivity()).get(SignUpViewModel::class.java)
        binding.signUpModel = viewModel
        binding.lifecycleOwner = this

        var callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        return binding.root
    }


}