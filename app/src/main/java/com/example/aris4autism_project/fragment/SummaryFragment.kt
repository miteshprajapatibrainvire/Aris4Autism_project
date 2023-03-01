package com.example.aris4autism_project.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.adapter.DiagnosAdapter
import com.example.aris4autism_project.adapter.DiagnosisAdapter
import com.example.aris4autism_project.databinding.FragmentSummaryBinding
import com.example.aris4autism_project.viewmodel.DiagnosisViewModel
import com.example.aris4autism_project.viewmodel.DiagnosisViewModelFactory
import com.example.aris4autism_project.viewmodel.LearnerViewModel

class SummaryFragment : Fragment() {


    lateinit var binding:FragmentSummaryBinding
    lateinit var viewmodelLearner: LearnerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSummaryBinding.inflate(inflater)
        viewmodelLearner=ViewModelProvider(requireActivity(),DiagnosisViewModelFactory(requireActivity())).get(LearnerViewModel::class.java)

        viewmodelLearner.getEditLearnerResponse(
            Constant.editUserId,
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWVkNWJhMDhkNmQwMTYyMDcyYTYwNzg4NTRiOTQwNjE2M2Q4NTkyMzRiMGMyOTA5NWFjOWIyMDE1MGQzYWMzZmFiNzdkZDQ0MDMzMGQzZWQiLCJpYXQiOjE2NzU3NTA1MDAsIm5iZiI6MTY3NTc1MDUwMCwiZXhwIjoxNzA3Mjg2NTAwLCJzdWIiOiI5MzMiLCJzY29wZXMiOltdfQ.D_YETTNEt8ZehNHmU15bY5IAPy8QTC3ZV9YzhIrX3BZC2C6YV6W1QjYF5NfnIttEb7dqD-kpWn9llGnk7mIw29hmfdmfUN0yQeN2SPSMQgQdcoauqLfQAktU9nn5D6MyBVHgwA9iI5NvxoyrodWZ4zp6G_SEuGUzmVpSEdcPccKnlHtPHmsGhEcahngaIrF0tPfLrB0AuCXhmb1p9rJNnCkfoCvK-R81E_dFR5pzm6z0jMm0rEExd0kjkvtrVfls8laKxR17JHP9gx4Qgm1P-9gMtfHPt4VqTq57QHYjoxFkog3btw6Qq7QizwkDJnIuAJYw6kHz1UDsyYXXhmVLhctaBLirzJxbT7tdy0W-ByOfu9okXv9CTnIREAbFBbopdoL0L0jF7TXx_8l6V0RBuZEsoQ8d0ohPRE7dTU3clKApA50zEqTTehQTHG-Ghzn97pO8lY5d2ti5xO1GS1lopKuSYP1WdiLd5clQ51EPDbed9CMT4k8fqVyZHOonq_ITAexDMl_mHB3rpPFM4MfpWbx3jVsaUSbxLvK-hpufggIJlEsRgSD8yZIA8wUqfGzcbbtVbf1omiKa-1sopcjcW36q48gY-ZM3RHH8-KA98P0AgkjPTtlKGOMIpbDNCaduuc3F5qbID8cpzFPkEj0VGL45EsIIaYuZI5WjwTXFRVE",
            "Android",
            "1"
        )

        viewmodelLearner.resultEditLearner.observe(requireActivity(),{
            when(it)
            {
                is BaseResponse.Success->{
                    Log.e("editlearnerData=",it.data!!.data.toString())
                    Glide.with(requireContext())
                        .load(it.data.data.getLearnerIcon.iconUrl)
                        .into(binding.imgIconId)

                    binding.idtxSummaryName.text=it.data!!.data.name
                    binding.idtxMale.text=it.data!!.data.gender
                    binding.idtxYear.text= Utils.dobToAge(it.data!!.data.dateOfBirth)
                    binding.idtxDob.text="DOB:"+it.data!!.data.dateOfBirth
                    binding.idtxMonthPlan.text=it.data!!.data.userSubscriptions.title
                    binding.idtxSubDataId.text="#"+it.data!!.data.subscriptionId.toString()
                    binding.idtxSubStartDate.text=it.data!!.data.userSubscriptions.startDate
                    binding.idtxEndDateId.text=it.data!!.data.userSubscriptions.endDate

                    binding.idRecyData.layoutManager=LinearLayoutManager(requireContext())
                    binding.idRecyData.adapter=DiagnosAdapter(it.data.data.getDiagnosisData)

                }
                is BaseResponse.Loading->{

                }
                is BaseResponse.Error->{
                    Toast.makeText(requireContext(), it.msg.toString(), Toast.LENGTH_SHORT).show()

                }
            }



        })


        return binding.root
    }


}