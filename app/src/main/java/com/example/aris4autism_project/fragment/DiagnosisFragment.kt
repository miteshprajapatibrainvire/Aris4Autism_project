package com.example.aris4autism_project.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.adapter.DiagnosisAdapter
import com.example.aris4autism_project.databinding.FragmentDiagnosisBinding
import com.example.aris4autism_project.model.DataXXXXXXXXXXXXXXXXXXXXXXXXX
import com.example.aris4autism_project.model.LearnerDiagnosisData
import com.example.aris4autism_project.viewmodel.DiagnosisViewModel
import com.example.aris4autism_project.viewmodel.DiagnosisViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class DiagnosisFragment : Fragment() {

    lateinit var binding: FragmentDiagnosisBinding
    lateinit var viewModel: DiagnosisViewModel
    var dianosisChecked:Boolean=false
    lateinit var refereshArray:ArrayList<DataXXXXXXXXXXXXXXXXXXXXXXXXX>


    companion object{
        var diagnosisArray=ArrayList<LearnerDiagnosisData>()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiagnosisBinding.inflate(inflater)
        refereshArray= ArrayList()

        val constDialog=Constant.getDialogCustom(requireContext())

        binding.btnSummary.setOnClickListener {
            if(dianosisChecked)
            {
                binding.recyIdDiagnosis.adapter= DiagnosisAdapter(refereshArray,{checkedState->getDianosis(checkedState)},"checkeditem")
                binding.recyIdDiagnosis.layoutManager=LinearLayoutManager(requireContext())

                val viewpager = activity?.findViewById<ViewPager2>(R.id.viewpagerID)
                viewpager?.currentItem = 2
                SummaryFragment().passArray(DiagnosisFragment.diagnosisArray)
                dianosisChecked=false
            }
            else
            {
                Constant.customDiagnosis(requireContext())
                binding.recyIdDiagnosis.adapter= DiagnosisAdapter(refereshArray,{checkedState->getDianosis(checkedState)},"changeBorder")
                binding.recyIdDiagnosis.layoutManager=LinearLayoutManager(requireContext())
                dianosisChecked=true
            }
        }

        if(binding.idYesbtn.isChecked)
        {
            binding.visibleDiagnosis.visibility=View.VISIBLE
           binding.visibleDiagnosisNotAvailable.visibility=View.GONE
        }
        if(binding.idNobtn.isChecked)
        {
            binding.visibleDiagnosis.visibility=View.GONE
            binding.visibleDiagnosisNotAvailable.visibility=View.VISIBLE
        }

        binding.toggle.setOnCheckedChangeListener { group, checkedId ->

            var radioBtn:RadioButton=group.findViewById(checkedId)
            if(radioBtn.text.toString().equals("Yes",true))
            {
                binding.visibleDiagnosis.visibility=View.VISIBLE
               binding.visibleDiagnosisNotAvailable.visibility=View.GONE
            }
            else
            {
                binding.visibleDiagnosis.visibility=View.GONE
                binding.visibleDiagnosisNotAvailable.visibility=View.VISIBLE
            }
           // Toast.makeText(requireContext(), radioBtn.text.toString(), Toast.LENGTH_SHORT).show()
        }

        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav?.visibility = View.GONE

        viewModel =
            ViewModelProvider(requireActivity(), DiagnosisViewModelFactory(requireContext())).get(
                DiagnosisViewModel::class.java
            )

        if(Utils.isOnline(requireContext()))
        {
            viewModel.getDiagnosisDetailForUser(
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWVkNWJhMDhkNmQwMTYyMDcyYTYwNzg4NTRiOTQwNjE2M2Q4NTkyMzRiMGMyOTA5NWFjOWIyMDE1MGQzYWMzZmFiNzdkZDQ0MDMzMGQzZWQiLCJpYXQiOjE2NzU3NTA1MDAsIm5iZiI6MTY3NTc1MDUwMCwiZXhwIjoxNzA3Mjg2NTAwLCJzdWIiOiI5MzMiLCJzY29wZXMiOltdfQ.D_YETTNEt8ZehNHmU15bY5IAPy8QTC3ZV9YzhIrX3BZC2C6YV6W1QjYF5NfnIttEb7dqD-kpWn9llGnk7mIw29hmfdmfUN0yQeN2SPSMQgQdcoauqLfQAktU9nn5D6MyBVHgwA9iI5NvxoyrodWZ4zp6G_SEuGUzmVpSEdcPccKnlHtPHmsGhEcahngaIrF0tPfLrB0AuCXhmb1p9rJNnCkfoCvK-R81E_dFR5pzm6z0jMm0rEExd0kjkvtrVfls8laKxR17JHP9gx4Qgm1P-9gMtfHPt4VqTq57QHYjoxFkog3btw6Qq7QizwkDJnIuAJYw6kHz1UDsyYXXhmVLhctaBLirzJxbT7tdy0W-ByOfu9okXv9CTnIREAbFBbopdoL0L0jF7TXx_8l6V0RBuZEsoQ8d0ohPRE7dTU3clKApA50zEqTTehQTHG-Ghzn97pO8lY5d2ti5xO1GS1lopKuSYP1WdiLd5clQ51EPDbed9CMT4k8fqVyZHOonq_ITAexDMl_mHB3rpPFM4MfpWbx3jVsaUSbxLvK-hpufggIJlEsRgSD8yZIA8wUqfGzcbbtVbf1omiKa-1sopcjcW36q48gY-ZM3RHH8-KA98P0AgkjPTtlKGOMIpbDNCaduuc3F5qbID8cpzFPkEj0VGL45EsIIaYuZI5WjwTXFRVE",
                "Android",
                "1"
            )
        }
        else
        {
            Utils.InternetNotAvailableToast(requireContext())
        }

        viewModel.resultDiagnosisData.observe(requireActivity()) {
            when (it) {
                is BaseResponse.Success -> {
                    Log.e("diagnosis response=", it.data?.data.toString())
                    refereshArray=it.data!!.data
                    binding.recyIdDiagnosis.layoutManager = LinearLayoutManager(requireContext())
                    binding.recyIdDiagnosis.adapter = it.data?.let { it1 -> DiagnosisAdapter(it1.data,{checkedState->getDianosis(checkedState)},"borderErrorGone") }
                    constDialog.cancel()
                }
                is BaseResponse.Loading -> {
                    constDialog.show()
                }
                is BaseResponse.Error -> {
                    Toast.makeText(requireContext(),it.msg.toString(), Toast.LENGTH_SHORT).show()
                    constDialog.cancel()
                }
            }
        }

        return binding.root
    }

    private fun getDianosis(checkedState: Boolean) {
        dianosisChecked=checkedState
    }


}