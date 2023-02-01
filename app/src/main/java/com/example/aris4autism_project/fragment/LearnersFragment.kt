package com.example.aris4autism_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.LearnerAdapter
import com.example.aris4autism_project.databinding.FragmentLearnersBinding
import com.example.aris4autism_project.model.LearnerModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class LearnersFragment : Fragment() {

    lateinit var binding:FragmentLearnersBinding
    lateinit var buttonView: BottomNavigationView
    lateinit var includeData:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLearnersBinding.inflate(layoutInflater, container, false)
        buttonView=requireActivity().findViewById(R.id.bottom_navigation)
        buttonView.visibility=View.VISIBLE
        includeData= activity?.findViewById(R.id.idDataLayout)!!
        includeData.visibility=View.VISIBLE
        var modelLearner=ArrayList<LearnerModel>()
        modelLearner.add(LearnerModel("Raj","Male","27 Years 7 Months","DOB: 06-30-1995","#2136 - 6 Months Plan","09 Dec 2022 to 09 Jun 2023"))
        modelLearner.add(LearnerModel("Kk","Male","2 Years 1 Months","DOB: 12-07-1995","#2136 - 3 Months Plan","09 Dec 2022 to 09 Mar 2023"))
        modelLearner.add(LearnerModel("Fezzy","Male","27 Years 10 Months","DOB: 03-31-1995","#2135 - 3 Months Plan","09 Dec 2022 to 09 Mar 2023"))

        binding.recyLearnId.layoutManager=LinearLayoutManager(requireActivity())
        binding.recyLearnId.adapter=LearnerAdapter(modelLearner)


        return binding.root
    }


}