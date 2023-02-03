package com.example.aris4autism_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.SubUserAdapter
import com.example.aris4autism_project.databinding.FragmentSubuserBinding
import com.example.aris4autism_project.model.DiagnosisModel
import com.example.aris4autism_project.model.LearnerModel
import com.example.aris4autism_project.model.SubUserModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class SubuserFragment : Fragment() {

    lateinit var binding:FragmentSubuserBinding
    lateinit var buttonView: BottomNavigationView
    lateinit var includeData:View
    lateinit var subUserList:ArrayList<SubUserModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSubuserBinding.inflate(inflater,container,false)
        buttonView=requireActivity().findViewById(R.id.bottom_navigation)
        buttonView.visibility=View.VISIBLE

        includeData= activity?.findViewById(R.id.idDataLayout)!!
        includeData.visibility=View.VISIBLE


        subUserList=ArrayList()
        var assignModel=ArrayList<LearnerModel>()
        var assignModel2=ArrayList<LearnerModel>()
        var assignModel3=ArrayList<LearnerModel>()

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

        assignModel.add(LearnerModel(231,R.drawable.profileimg3,"Mitesh","Male","27 Years 7 Months","DOB: 06-30-1995","#2130 - 6 Months Plan","09 Dec 2022 to 09 Jun 2023",true,"09 Dec 2022","09 Jun 2023",dataArr))
        assignModel2.add(LearnerModel(232,R.drawable.profileimggirl4,"Jin","Male","2 Years 1 Months","DOB: 12-07-1995","#2131 - 3 Months Plan","09 Dec 2022 to 09 Mar 2023",true,"09 Dec 2022","09 Mar 2023",dataArr2))
        assignModel3.add(LearnerModel(233,R.drawable.profileimg4,"jack","Male","27 Years 10 Months","DOB: 03-31-1995","#2132 - 3 Months Plan","09 Dec 2022 to 09 Mar 2023",false,"09 Dec 2022","09 Mar 2023",dataArr3))

        val subUserModel=SubUserModel(R.drawable.profileimg1,"Jack","jack5@gmail.com","9565545652","Assigned learner",
            arrayListOf("jack","makro","jin"),
            assignModel
        )
        val subUserModel2=SubUserModel(R.drawable.profilegirlimg2,"Jin","Jin55@gmail.com","9090656568","Assigned learner",
            arrayListOf("semi","arlo","rina"),
            assignModel2
        )
        val subUserModel3=SubUserModel(R.drawable.profilegirlimg3,"arlo","arlo65@gmail.com","8659645658","Assigned learner",
            arrayListOf("jen","henk","sindy"),
            assignModel3
        )

        subUserList.add(subUserModel)
        subUserList.add(subUserModel2)
        subUserList.add(subUserModel3)

        binding.recySubUser.adapter=SubUserAdapter(subUserList)
        binding.recySubUser.layoutManager=LinearLayoutManager(requireContext())

        return binding.root
    }


}