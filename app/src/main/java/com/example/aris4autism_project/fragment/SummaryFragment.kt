package com.example.aris4autism_project.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.adapter.DiagnosAdapter
import com.example.aris4autism_project.databinding.FragmentSummaryBinding
import com.example.aris4autism_project.model.CreateNewLearnerModel
import com.example.aris4autism_project.model.diagnosismodel.DiagnosisInnerData
import com.example.aris4autism_project.model.learnermodel.LearnerDiagnosisData
import com.example.aris4autism_project.viewmodel.DiagnosisViewModelFactory
import com.example.aris4autism_project.viewmodel.LearnerViewModel
import com.example.aris4autism_project.viewmodel.LearnerViewModelFactory

class SummaryFragment : Fragment() {


    lateinit var binding: FragmentSummaryBinding
    lateinit var viewmodelLearner: LearnerViewModel
    lateinit var recyDiagnosis: RecyclerView

    companion object {
        var name: String = ""
        var dateOfBirth: String = ""
        var gender: String = ""
        var subscriptionId: String = ""
        var startDob: String = ""
        var endDobData: String = ""
        var monthlyPlan: String = ""
    }

    var diagnosisArraySummary = ArrayList<DiagnosisInnerData>()
    lateinit var addviewModel: LearnerViewModel
    var diagnosisId = ArrayList<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSummaryBinding.inflate(layoutInflater)

        attachRecyclerView(binding)
    }

    private fun attachRecyclerView(binding: FragmentSummaryBinding) {
//        binding.idRecyData.layoutManager = LinearLayoutManager(requireContext())
//        binding.idRecyData.adapter = DiagnosAdapter(diagnosisArraySummary as ArrayList<LearnerDiagnosisData>)
        Log.e("diagnosisArray=", DiagnosisFragment.diagnosisArray.toString())
        binding.idRecyData.layoutManager = LinearLayoutManager(requireContext())
        var learnerDiagnosisCast=ArrayList<LearnerDiagnosisData>()
        for(i in DiagnosisFragment.diagnosisArray)
        {
            learnerDiagnosisCast.add(LearnerDiagnosisData(i.id,0,0,i.title,i.slug))
        }
        //Log.e("learnerSummaryArray=",learnerDiagnosisCast.toString())
        binding.idRecyData.adapter = DiagnosAdapter(learnerDiagnosisCast)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSummaryBinding.inflate(inflater)
        viewmodelLearner =
            ViewModelProvider(requireActivity(), DiagnosisViewModelFactory(requireActivity())).get(
                LearnerViewModel::class.java
            )
        recyDiagnosis = binding.idRecyData
        addviewModel =
            ViewModelProvider(requireActivity(), LearnerViewModelFactory(requireContext())).get(
                LearnerViewModel::class.java
            )

        Handler(Looper.getMainLooper()).postDelayed({
            Log.e("diagnosisArray=", DiagnosisFragment.diagnosisArray.toString())
            binding.idRecyData.layoutManager = LinearLayoutManager(requireContext())
            var learnerDiagnosisCast=ArrayList<LearnerDiagnosisData>()
            for(i in DiagnosisFragment.diagnosisArray.distinct())
            {
                learnerDiagnosisCast.add(LearnerDiagnosisData(i.id,0,0,i.title,i.slug))
            }
            Log.e("learnerSummaryArray=",learnerDiagnosisCast.toString())
            binding.idRecyData.adapter = DiagnosAdapter(learnerDiagnosisCast)
        }, 2000)

        binding.idAddBtn.setOnClickListener {
            addviewModel.addNewLearner(
                CreateNewLearnerModel(
                    "1",
                    SummaryFragment.name,
                    SummaryFragment.gender.toLowerCase(),
                    SummaryFragment.dateOfBirth,
                    "24 Year",
                    "4",
                    SummaryFragment().diagnosisId,
                    ""
                )
            )
        }

//
//        addviewModel.resultNewLearner.observe(requireActivity(), {
//            when (it) {
//                is BaseResponse.Success -> {
//                    Log.e("AddNewResponsedata=", it.data!!.meta.toString())
//                }
//                is BaseResponse.Error -> {
//                    Log.e("Error=", it.msg.toString())
//                    if(it.msg==null)
//                    {
//                        Toast.makeText(requireContext(), "Server side error", Toast.LENGTH_SHORT).show()
//                    }
//                    //Log.e("serverError=", it.data!!.meta.toString())
//                }
//                is BaseResponse.Loading -> {
//
//                }
//            }
//        })

//        viewmodelLearner.getEditLearnerResponse(
//            Constant.editUserId,
//            "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWVkNWJhMDhkNmQwMTYyMDcyYTYwNzg4NTRiOTQwNjE2M2Q4NTkyMzRiMGMyOTA5NWFjOWIyMDE1MGQzYWMzZmFiNzdkZDQ0MDMzMGQzZWQiLCJpYXQiOjE2NzU3NTA1MDAsIm5iZiI6MTY3NTc1MDUwMCwiZXhwIjoxNzA3Mjg2NTAwLCJzdWIiOiI5MzMiLCJzY29wZXMiOltdfQ.D_YETTNEt8ZehNHmU15bY5IAPy8QTC3ZV9YzhIrX3BZC2C6YV6W1QjYF5NfnIttEb7dqD-kpWn9llGnk7mIw29hmfdmfUN0yQeN2SPSMQgQdcoauqLfQAktU9nn5D6MyBVHgwA9iI5NvxoyrodWZ4zp6G_SEuGUzmVpSEdcPccKnlHtPHmsGhEcahngaIrF0tPfLrB0AuCXhmb1p9rJNnCkfoCvK-R81E_dFR5pzm6z0jMm0rEExd0kjkvtrVfls8laKxR17JHP9gx4Qgm1P-9gMtfHPt4VqTq57QHYjoxFkog3btw6Qq7QizwkDJnIuAJYw6kHz1UDsyYXXhmVLhctaBLirzJxbT7tdy0W-ByOfu9okXv9CTnIREAbFBbopdoL0L0jF7TXx_8l6V0RBuZEsoQ8d0ohPRE7dTU3clKApA50zEqTTehQTHG-Ghzn97pO8lY5d2ti5xO1GS1lopKuSYP1WdiLd5clQ51EPDbed9CMT4k8fqVyZHOonq_ITAexDMl_mHB3rpPFM4MfpWbx3jVsaUSbxLvK-hpufggIJlEsRgSD8yZIA8wUqfGzcbbtVbf1omiKa-1sopcjcW36q48gY-ZM3RHH8-KA98P0AgkjPTtlKGOMIpbDNCaduuc3F5qbID8cpzFPkEj0VGL45EsIIaYuZI5WjwTXFRVE",
//            "Android",
//            "1"
//        )

        if (name != null) {
            binding.idtxSummaryName.text = name
            binding.idtxMale.text = gender

            binding.idtxYear.text =Utils.dobToAge(dateOfBirth)
            binding.idtxDob.text = "DOB:" + dateOfBirth
            binding.idtxMonthPlan.text = monthlyPlan
            binding.idtxSubStartDate.text = startDob
            binding.idtxEndDateId.text = endDobData
            binding.idtxSubDataId.text = subscriptionId
        }
        var diagnosisArray: ArrayList<com.example.aris4autism_project.model.learnermodel.LearnerDiagnosisData> = DiagnosisFragment.diagnosisArray as ArrayList<LearnerDiagnosisData>
        Log.e("daignosisPass Data=", DiagnosisFragment.diagnosisArray.toString())

/*        viewmodelLearner.resultEditLearner.observe(requireActivity(), {
            when (it) {
                is BaseResponse.Success -> {
                    Log.e("editlearnerData=", it.data!!.data.toString())
                    Glide.with(requireContext())
                        .load(it.data.data.getLearnerIcon.iconUrl)
                        .into(binding.imgIconId)*/

//                    binding.idtxSummaryName.text=it.data!!.data.name
//                    binding.idtxMale.text=it.data!!.data.gender
//                    binding.idtxYear.text= Utils.dobToAge(it.data!!.data.dateOfBirth)
//                    binding.idtxDob.text="DOB:"+it.data!!.data.dateOfBirth
//                    binding.idtxMonthPlan.text=it.data!!.data.userSubscriptions.title
//                    binding.idtxSubDataId.text="#"+it.data!!.data.subscriptionId.toString()
//                    binding.idtxSubStartDate.text=it.data!!.data.userSubscriptions.startDate
//                    binding.idtxEndDateId.text=it.data!!.data.userSubscriptions.endDate
//                    binding.idRecyData.layoutManager=LinearLayoutManager(requireContext())
//                    binding.idRecyData.adapter=DiagnosAdapter(it.data.data.getDiagnosisData)

             /*   }
                is BaseResponse.Loading -> {
                }
                is BaseResponse.Error -> {
                    Toast.makeText(requireContext(), it.msg.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })*/

        return binding.root
    }


    fun setSummaryLayoutData(
        name: String,
        gender: String,
        dob: String,
        subscriptionId: String,
        dobStart: String,
        dobEnd: String,
        monthlyPlan: String
    ) {
        SummaryFragment.name = name
        // binding.idtxSummaryName.text=name
        SummaryFragment.gender = gender
        // binding.idtxMale.text=gender
        SummaryFragment.dateOfBirth = dob
        //binding.idtxYear.text=Utils.dobToAge(dob)
        SummaryFragment.subscriptionId = subscriptionId
        // binding.idtxMonthPlan.text=monthlyPlan
        SummaryFragment.startDob = dobStart
        SummaryFragment.monthlyPlan = monthlyPlan
        SummaryFragment.endDobData = dobEnd
        //binding.idtxSubStartDate.text=dobStart
        // binding.idtxEndDateId.text=dobEnd

        Log.e(
            "passingLog=",
            "name=" + name + "=gender=" + gender + "=dateofbirth=" + dob + "subscription=" + subscriptionId + "s=startDob=" + startDob + "montlyplan=" + monthlyPlan + "=endDob=" + dobEnd
        )

    }

    fun passArray(slist: ArrayList<DiagnosisInnerData>) {
        diagnosisArraySummary = slist
        Log.e("diagnosi=", diagnosisArraySummary.toString())


    }
}