package com.example.aris4autism_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.OverViewAdapter
import com.example.aris4autism_project.databinding.FragmentOverviewBinding
import com.example.aris4autism_project.model.DiagnosisModel
import com.example.aris4autism_project.model.OverViewModel
import com.example.aris4autism_project.model.PastSubscriptionModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class OverviewFragment : Fragment() {

    lateinit var binding:FragmentOverviewBinding
    lateinit var buttonView: BottomNavigationView
    lateinit var includeData:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverviewBinding.inflate(layoutInflater, container, false)

        buttonView=requireActivity().findViewById(R.id.bottom_navigation)
        buttonView.visibility=View.VISIBLE

        includeData= activity?.findViewById(R.id.idDataLayout)!!
        includeData.visibility=View.VISIBLE

        var modelOverView=ArrayList<OverViewModel>()

        var arrData1=ArrayList<DiagnosisModel>()
        arrData1.add(DiagnosisModel("Dev"))
        arrData1.add(DiagnosisModel("Backend"))
        arrData1.add(DiagnosisModel("Testing"))

        var arrData2=ArrayList<DiagnosisModel>()
        arrData2.add(DiagnosisModel("Dev"))
        arrData2.add(DiagnosisModel("Backend"))
        arrData2.add(DiagnosisModel("Testing"))
        arrData2.add(DiagnosisModel("Anxiety"))

        var arrData3=ArrayList<DiagnosisModel>()
        arrData3.add(DiagnosisModel("Dev"))
        arrData3.add(DiagnosisModel("Backend"))
        arrData3.add(DiagnosisModel("Testing"))

        val pastSubModel=ArrayList<PastSubscriptionModel>()
        pastSubModel.add(PastSubscriptionModel(112,"6 month","568595","1 Jun 2021","3 march 2021",false))
        pastSubModel.add(PastSubscriptionModel(113,"2 month","231565","2 Jun 2021","22 march 2021",false))
        pastSubModel.add(PastSubscriptionModel(114,"4 month","221254","4 Jun 2021","26 march 2021",false))
        pastSubModel.add(PastSubscriptionModel(115,"3 month","213256","5 Jun 2021","25 march 2021",false))

        modelOverView.add(OverViewModel(231,R.drawable.profileimg3,"Arlo","Male","27 Years 7 Months","DOB: 06-30-1995","#2130 - 6 Months Plan","09 Dec 2022 to 09 Jun 2023",true,"09 Dec 2022","09 Jun 2023",arrData1,ArrayList<PastSubscriptionModel>()))
        modelOverView.add(OverViewModel(232,R.drawable.profileimggirl4,"Jin","Female","2 Years 1 Months","DOB: 12-07-1995","#2131 - 3 Months Plan","09 Dec 2022 to 09 Mar 2023",true,"09 Dec 2022","09 Mar 2023",arrData2,ArrayList<PastSubscriptionModel>()))
        modelOverView.add(OverViewModel(233,R.drawable.profileimg1,"jack","Male","27 Years 10 Months","DOB: 03-31-1995","#2132 - 3 Months Plan","09 Dec 2022 to 09 Mar 2023",false,"09 Dec 2022","09 Mar 2023",arrData3,pastSubModel))
        modelOverView.add(OverViewModel(233,R.drawable.profileimg4,"Jimmy","Male","27 Years 10 Months","DOB: 03-31-1995","#2132 - 4 Months Plan","09 Dec 2022 to 09 Mar 2023",true,"09 Dec 2022","09 Mar 2023",arrData3,ArrayList<PastSubscriptionModel>()))

        binding.recyOverView.layoutManager=LinearLayoutManager(requireActivity())
        binding.recyOverView.adapter=OverViewAdapter(modelOverView)

        val callback=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                activity!!.finish()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)

        return binding.root
    }
}