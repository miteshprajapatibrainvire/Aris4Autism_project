package com.example.aris4autism_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aris4autism_project.databinding.FragmentLearnerOverViewBinding

class LearnerOverViewFragment : Fragment() {

    lateinit var binding:FragmentLearnerOverViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentLearnerOverViewBinding.inflate(layoutInflater,container,false)



        return binding.root
    }


}