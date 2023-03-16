package com.example.aris4autism_project.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.adapter.DiagnosAdapter
import com.example.aris4autism_project.databinding.FragmentSummaryBinding
import com.example.aris4autism_project.model.diagnosismodel.DiagnosisInnerData
import com.example.aris4autism_project.model.learnermodel.AddNewLearnerResponse
import com.example.aris4autism_project.model.learnermodel.CreateNewLearnerModel
import com.example.aris4autism_project.model.learnermodel.LearnerDiagnosisData
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
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

        Log.e("diagnosisArray=", DiagnosisFragment.diagnosisArray.toString())
        binding.idRecyData.layoutManager = LinearLayoutManager(requireContext())
        var learnerDiagnosisCast=ArrayList<LearnerDiagnosisData>()
        for(i in DiagnosisFragment.diagnosisArray)
        {
            learnerDiagnosisCast.add(LearnerDiagnosisData(i.id,0,0,i.title,i.slug))
        }
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
            binding.idRecyData.layoutManager = LinearLayoutManager(requireContext())
            var learnerDiagnosisCast=ArrayList<LearnerDiagnosisData>()
            for(i in DiagnosisFragment.diagnosisArray.distinct())
            {
                learnerDiagnosisCast.add(LearnerDiagnosisData(i.id,0,0,i.title,i.slug))
            }
            binding.idRecyData.adapter = DiagnosAdapter(learnerDiagnosisCast)
        }, 2000)

        binding.idAddBtn.setOnClickListener {
            addviewModel.addNewLearner(
                CreateNewLearnerModel(
                    "1",
                    name,
                    gender.toLowerCase(),
                    dateOfBirth,
                    "24 Year",
                    "4",
                    SummaryFragment().diagnosisId,
                    ""
                )
            )
        }

        addviewModel.resultNewLearner.observe(viewLifecycleOwner,{state->
            when(state)
            {
                is ResponseHandler.Loading->{

                }
                 is ResponseHandler.OnFailed->{

                 }
                is ResponseHandler.OnSuccessResponse<ResponseData<AddNewLearnerResponse>?>->
                {
                    Log.e("responseAddLearnerData=", state.response?.data?.data.toString())
                }
        }})



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