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
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.adapter.DiagnosAdapter
import com.example.aris4autism_project.databinding.FragmentSummaryBinding
import com.example.aris4autism_project.model.diagnosismodel.DiagnosisDetailResponseModel
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
    lateinit var recyclerview:RecyclerView



    companion object {
        var name: String = ""
        var dateOfBirth: String = ""
        var gender: String = ""
        var subscriptionId: String = ""
        var startDob: String = ""
        var endDobData: String = ""
        var monthlyPlan: String = ""
    }

    private var diagnosisDataLearner=ArrayList<LearnerDiagnosisData>()
    private var diagnosisArraySummary = ArrayList<DiagnosisDetailResponseModel>()
    lateinit var addviewModel: LearnerViewModel
    private var diagnosisId = ArrayList<String>()
    private var daignosisAdp:DiagnosAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSummaryBinding.inflate(inflater,container,false)
        viewmodelLearner =
            ViewModelProvider(requireActivity(), DiagnosisViewModelFactory(requireActivity())).get(
                LearnerViewModel::class.java
            )

        addviewModel =
            ViewModelProvider(requireActivity(), LearnerViewModelFactory(requireContext())).get(
                LearnerViewModel::class.java
            )

        Toast.makeText(requireContext(), "summary", Toast.LENGTH_SHORT).show()

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

//            Log.e("diagnosisArrayData=", DiagnosisFragment.diagnosisArray.toString())
//            var learnerDiagnosisCast=ArrayList<LearnerDiagnosisData>()
//
//            for(i in diagnosisArraySummary.distinct())
//            {
//                learnerDiagnosisCast.add(LearnerDiagnosisData(i.id,0,0,i.title,i.slug))
//            }
//            binding.idRecyData.adapter = DiagnosAdapter(diagnosisArraySummary)
//            binding.idRecyData.layoutManager = LinearLayoutManager(requireContext())

        addviewModel.resultNewLearner.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseHandler.Loading -> {

                }
                is ResponseHandler.OnFailed -> {

                }
                is ResponseHandler.OnSuccessResponse<ResponseData<AddNewLearnerResponse>?> -> {
                    Log.e("responseAddLearnerData=", state.response?.data?.data.toString())
                }
            }
        }

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

            Log.e("dianosisarraysummmary=",diagnosisArraySummary.toString())
//            for(i in diagnosisArraySummary)
//            {
//                diagnosisDataLearner.add(LearnerDiagnosisData(i.id,0,0,i.title," "))
//            }
            Log.e("diagnosisHandlerArray=",diagnosisDataLearner.toString())
            binding.idRecyData.layoutManager = LinearLayoutManager(requireContext())
            daignosisAdp = DiagnosAdapter(diagnosisDataLearner)
            binding.idRecyData.adapter =daignosisAdp

//        var diagnosisArray: ArrayList<com.example.aris4autism_project.model.learnermodel.LearnerDiagnosisData> = DiagnosisFragment.diagnosisArray as ArrayList<LearnerDiagnosisData>
//        Log.e("daignosisPass Data=", DiagnosisFragment.diagnosisArray.toString())
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
        SummaryFragment.gender = gender
        SummaryFragment.dateOfBirth = dob
        SummaryFragment.subscriptionId = subscriptionId
        SummaryFragment.startDob = dobStart
        SummaryFragment.monthlyPlan = monthlyPlan
        SummaryFragment.endDobData = dobEnd
        Log.e(
            "passingLog=",
            "name=" + name + "=gender=" + gender + "=dateofbirth=" + dob + "subscription=" + subscriptionId + "s=startDob=" + startDob + "montlyplan=" + monthlyPlan + "=endDob=" + dobEnd
        )
    }

    fun passDiagnosisArray(slist: ArrayList<DiagnosisDetailResponseModel>) {

        for (i in slist.indices){
            if (slist[i].isItemChecked)
            {
                diagnosisArraySummary.add(slist[i])
            }
        }

        for(i in diagnosisArraySummary)
        {
            diagnosisDataLearner.add(LearnerDiagnosisData(i.id,0,0,i.title," "))
        }

        //daignosisAdp = DiagnosAdapter(diagnosisDataLearner)
//        binding.idRecyData.adapter =daignosisAdp
        daignosisAdp?.setArrayList(diagnosisDataLearner)
//        daignosisAdp= DiagnosAdapter(diagnosisDataLearner)
       daignosisAdp?.notifyDataSetChanged()
        Log.e("diagnosi=", diagnosisArraySummary.toString())
        Log.i("TAG", "passDiagnosisArray: ${diagnosisDataLearner.size}")
    }
}