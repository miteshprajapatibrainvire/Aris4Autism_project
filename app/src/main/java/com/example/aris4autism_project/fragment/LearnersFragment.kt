package com.example.aris4autism_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.LearnerAdapter
import com.example.aris4autism_project.databinding.FragmentLearnersBinding
import com.example.aris4autism_project.model.DiagnosisModel
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
        var dataArr=ArrayList<DiagnosisModel>()
        dataArr.add(DiagnosisModel("Dev"))
        dataArr.add(DiagnosisModel("Backend"))
        dataArr.add(DiagnosisModel("Testing"))
        var dataArr2=ArrayList<DiagnosisModel>()
        dataArr2.add(DiagnosisModel("Dev"))
        dataArr2.add(DiagnosisModel("Backend"))
        dataArr2.add(DiagnosisModel("Testing"))
        dataArr2.add(DiagnosisModel("Anxiety"))
        var dataArr3=ArrayList<DiagnosisModel>()
        dataArr3.add(DiagnosisModel("Dev"))
        dataArr3.add(DiagnosisModel("Backend"))
        dataArr3.add(DiagnosisModel("Testing"))
        modelLearner.add(LearnerModel(231,"Mitesh","Male","27 Years 7 Months","DOB: 06-30-1995","#2130 - 6 Months Plan","09 Dec 2022 to 09 Jun 2023",true,"09 Dec 2022","09 Jun 2023",dataArr))
        modelLearner.add(LearnerModel(232,"Jin","Male","2 Years 1 Months","DOB: 12-07-1995","#2131 - 3 Months Plan","09 Dec 2022 to 09 Mar 2023",false,"09 Dec 2022","09 Mar 2023",dataArr2))
        modelLearner.add(LearnerModel(233,"jack","Male","27 Years 10 Months","DOB: 03-31-1995","#2132 - 3 Months Plan","09 Dec 2022 to 09 Mar 2023",true,"09 Dec 2022","09 Mar 2023",dataArr3))

        binding.recyLearnId.layoutManager=LinearLayoutManager(requireActivity())
        binding.recyLearnId.adapter=LearnerAdapter(requireActivity(),modelLearner)

        val callback=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)



        return binding.root
    }


}