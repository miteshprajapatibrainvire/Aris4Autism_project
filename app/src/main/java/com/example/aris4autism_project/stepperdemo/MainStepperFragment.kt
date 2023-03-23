package com.example.aris4autism_project.stepperdemo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.FragmentMainStepperBinding
import nl.isaac.android.StepIndicator


class MainStepperFragment : Fragment(),SendArrayList {

    lateinit var binding:FragmentMainStepperBinding
    lateinit var adapter:StepperAdp

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentMainStepperBinding.inflate(layoutInflater)

        adapter= StepperAdp(requireActivity())
        adapter.addFragment(step1Fragment(),"step1")
        adapter.addFragment(step2Fragment(),"step2")
        adapter.addFragment(step3Fragment(),"step3")
        binding.idViewpagerData.adapter=adapter

//        viewpager=binding.idViewpagerData

        val stepIndicatorIcons: StepIndicator = binding.idLayoutToolbar.stepIndicatorNumbersAddnewLearner

        //set stepperchecked number indicator with color,label,text
        stepIndicatorIcons.apply {
            setupWithViewPager(binding.idViewpagerData)
            showLabels = true
            labels = listOf("Step 1", "Step 2","Step 3")
            fillNextStep = false
            fillPreviousStep = true
            activeStepColor= ContextCompat.getColor(context,R.color.darkblue)
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

    override fun sendArrayListData(sm: String) {
        var f=FragmentActivity().supportFragmentManager?.findFragmentByTag("switchArray") as step2Fragment
        f.displayArrayList("Hello")
        binding.idViewpagerData.currentItem = binding.idViewpagerData.currentItem+1
    }



}


