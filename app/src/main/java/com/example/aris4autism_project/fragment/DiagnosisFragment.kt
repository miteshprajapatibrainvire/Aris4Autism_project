package com.example.aris4autism_project.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.adapter.DiagnosisAdapter
import com.example.aris4autism_project.databinding.FragmentDiagnosisBinding
import com.example.aris4autism_project.model.diagnosismodel.DiagnosisDetailResponseModel
import com.example.aris4autism_project.model.diagnosismodel.DiagnosisInnerData
import com.example.aris4autism_project.model.responsemodel.ResponseData
import com.example.aris4autism_project.model.responsemodel.ResponseHandler
import com.example.aris4autism_project.viewmodel.DiagnosisViewModel
import com.example.aris4autism_project.viewmodel.DiagnosisViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class DiagnosisFragment : Fragment() {

    lateinit var binding: FragmentDiagnosisBinding
    lateinit var viewModel: DiagnosisViewModel
    var dianosisChecked: Boolean = false
    lateinit var refereshArray: ArrayList<DiagnosisInnerData>
    var responseArrayData = ArrayList<DiagnosisInnerData>()

    companion object {
        var diagnosisArray = ArrayList<DiagnosisInnerData>()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiagnosisBinding.inflate(inflater)
        refereshArray = ArrayList()

        val constDialog = Constant.getDialogCustom(requireContext())

        binding.btnSummary.setOnClickListener {
            if (dianosisChecked) {
                binding.recyIdDiagnosis.adapter = DiagnosisAdapter(
                    responseArrayData,
                    { checkedState -> getDianosis(checkedState) },
                    "checkeditem"
                )
                binding.recyIdDiagnosis.layoutManager = LinearLayoutManager(requireContext())

                val viewpager = activity?.findViewById<ViewPager2>(R.id.viewpagerID)
                viewpager?.currentItem = 2
                SummaryFragment().passArray(DiagnosisFragment.diagnosisArray)
                dianosisChecked = false
            } else {
                Constant.customDiagnosis(requireContext())
                binding.recyIdDiagnosis.adapter = DiagnosisAdapter(
                    responseArrayData,
                    { checkedState -> getDianosis(checkedState) },
                    "changeBorder"
                )
                binding.recyIdDiagnosis.layoutManager = LinearLayoutManager(requireContext())
                dianosisChecked = true
            }
        }

        if (binding.idYesbtn.isChecked) {
            binding.visibleDiagnosis.visibility = View.VISIBLE
            binding.visibleDiagnosisNotAvailable.visibility = View.GONE
        }
        if (binding.idNobtn.isChecked) {
            binding.visibleDiagnosis.visibility = View.GONE
            binding.visibleDiagnosisNotAvailable.visibility = View.VISIBLE
        }

        binding.toggle.setOnCheckedChangeListener { group, checkedId ->

            var radioBtn: RadioButton = group.findViewById(checkedId)
            if (radioBtn.text.toString().equals("Yes", true)) {
                binding.visibleDiagnosis.visibility = View.VISIBLE
                binding.visibleDiagnosisNotAvailable.visibility = View.GONE
            } else {
                binding.visibleDiagnosis.visibility = View.GONE
                binding.visibleDiagnosisNotAvailable.visibility = View.VISIBLE
            }
            // Toast.makeText(requireContext(), radioBtn.text.toString(), Toast.LENGTH_SHORT).show()
        }

        val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav?.visibility = View.GONE

        viewModel =
            ViewModelProvider(requireActivity(), DiagnosisViewModelFactory(requireContext())).get(
                DiagnosisViewModel::class.java
            )

        if (Utils.isOnline(requireContext())) {

            viewModel.getDiagnosisDetailForUser()

        } else {
            Utils.InternetNotAvailableToast(requireContext())
        }

        viewModel.resultDiagnosisData.observe(viewLifecycleOwner, { state ->
            when (state) {
                is ResponseHandler.Loading -> {

                }
                is ResponseHandler.OnFailed -> {

                }
                is ResponseHandler.OnSuccessResponse<ResponseData<List<DiagnosisDetailResponseModel>>?> -> {
                    Log.e("diagnosisList=", state.response?.data.toString())
                    responseArrayData.clear()
                    for (i in state.response?.data!!.indices)
                    {
                        if (state.response?.data!![i].slug != null)
                        {
                            if (state.response?.data!![i].title!=null)
                            {
                                responseArrayData.add(
                                    DiagnosisInnerData(
                                        state.response?.data!![i].id,
                                        state.response?.data!![i].title,
                                        state.response?.data!![i].slug
                                    )
                                )
                            }
                        }
                        //refereshArray.add(InnerData(state.4response?.data!![i].id,state.response?.data!![i].title,state.response?.data!![i].slug))
                    }
                    // Log.e("slugData==", responseArrayData.toString())
                    binding.recyIdDiagnosis.layoutManager = LinearLayoutManager(requireContext())
                    binding.recyIdDiagnosis.adapter = DiagnosisAdapter(
                        responseArrayData,
                        { checkedState -> getDianosis(checkedState) },
                        "borderErrorGone"
                    )

                }
            }
        })
        /*  viewModel.resultDiagnosisData.observe(requireActivity()) {
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
          }*/

        return binding.root
    }

    private fun getDianosis(checkedState: Boolean) {
        dianosisChecked = checkedState
    }


}