package com.example.aris4autism_project.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.OverViewAdapter
import com.example.aris4autism_project.databinding.FragmentOverviewBinding
import com.example.aris4autism_project.viewmodel.OverViewViewModel
import com.example.aris4autism_project.viewmodel.OverViewViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class OverviewFragment : Fragment() {

    lateinit var binding:FragmentOverviewBinding
    lateinit var buttonView: BottomNavigationView
    lateinit var includeData:View
    lateinit var viewModel: OverViewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverviewBinding.inflate(layoutInflater, container, false)

        buttonView=requireActivity().findViewById(R.id.bottom_navigation)
        buttonView.visibility=View.VISIBLE

        includeData= activity?.findViewById(R.id.idDataLayout)!!
        includeData.visibility=View.VISIBLE

        viewModel=ViewModelProvider(requireActivity(), OverViewViewModelFactory(requireActivity())).get(OverViewViewModel::class.java)

        viewModel.getOverViewDetails("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWVkNWJhMDhkNmQwMTYyMDcyYTYwNzg4NTRiOTQwNjE2M2Q4NTkyMzRiMGMyOTA5NWFjOWIyMDE1MGQzYWMzZmFiNzdkZDQ0MDMzMGQzZWQiLCJpYXQiOjE2NzU3NTA1MDAsIm5iZiI6MTY3NTc1MDUwMCwiZXhwIjoxNzA3Mjg2NTAwLCJzdWIiOiI5MzMiLCJzY29wZXMiOltdfQ.D_YETTNEt8ZehNHmU15bY5IAPy8QTC3ZV9YzhIrX3BZC2C6YV6W1QjYF5NfnIttEb7dqD-kpWn9llGnk7mIw29hmfdmfUN0yQeN2SPSMQgQdcoauqLfQAktU9nn5D6MyBVHgwA9iI5NvxoyrodWZ4zp6G_SEuGUzmVpSEdcPccKnlHtPHmsGhEcahngaIrF0tPfLrB0AuCXhmb1p9rJNnCkfoCvK-R81E_dFR5pzm6z0jMm0rEExd0kjkvtrVfls8laKxR17JHP9gx4Qgm1P-9gMtfHPt4VqTq57QHYjoxFkog3btw6Qq7QizwkDJnIuAJYw6kHz1UDsyYXXhmVLhctaBLirzJxbT7tdy0W-ByOfu9okXv9CTnIREAbFBbopdoL0L0jF7TXx_8l6V0RBuZEsoQ8d0ohPRE7dTU3clKApA50zEqTTehQTHG-Ghzn97pO8lY5d2ti5xO1GS1lopKuSYP1WdiLd5clQ51EPDbed9CMT4k8fqVyZHOonq_ITAexDMl_mHB3rpPFM4MfpWbx3jVsaUSbxLvK-hpufggIJlEsRgSD8yZIA8wUqfGzcbbtVbf1omiKa-1sopcjcW36q48gY-ZM3RHH8-KA98P0AgkjPTtlKGOMIpbDNCaduuc3F5qbID8cpzFPkEj0VGL45EsIIaYuZI5WjwTXFRVE","Android","1")

        viewModel.resultOverView.observe(requireActivity(),{
            when(it)
            {
               is BaseResponse.Success->{
                   Log.e("ResponseData=",it.data!!.data.original.data.toString())
                   binding.recyOverView.layoutManager=LinearLayoutManager(requireActivity())
                   binding.recyOverView.adapter=OverViewAdapter(it.data!!.data.original.data)
                   stopLoading()
               }

                is BaseResponse.Error->{
                    stopLoading()
                }

                is BaseResponse.Loading->{
                    showLoading()
                }
            }
        })

/*
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
    */

        val callback=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                activity!!.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        return binding.root
    }

    fun showLoading()
    {
        binding.prgbarOverview.visibility=View.VISIBLE
    }

    fun stopLoading()
    {
        binding.prgbarOverview.visibility=View.GONE
    }
}