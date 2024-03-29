package com.example.aris4autism_project.fragment

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
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
import com.example.aris4autism_project.model.SummaryPassModel
import com.example.aris4autism_project.model.diagnosismodel.DiagnosisDetailResponseModel
import com.example.aris4autism_project.model.diagnosismodel.DiagnosisInnerData
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.viewmodel.BasicDetailValidation
import com.example.aris4autism_project.viewmodel.BasicDetailValidationViewModelFactory
import com.example.aris4autism_project.viewmodel.DiagnosisViewModel
import com.example.aris4autism_project.viewmodel.DiagnosisViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class DiagnosisFragment : Fragment() {

    lateinit var binding: FragmentDiagnosisBinding
    lateinit var viewModel: DiagnosisViewModel
    var dianosisChecked: Boolean = false
    lateinit var refereshArray: ArrayList<DiagnosisInnerData>
    var responseArrayData = ArrayList<DiagnosisDetailResponseModel>()
    lateinit var adapterDiagnosis: DiagnosisAdapter
    val bundle = Bundle()
    val listSelectedDianogsisId: ArrayList<Int> = ArrayList()
    lateinit var viewmodelBasicValid: BasicDetailValidation

    companion object {
        var diagnosisArray = ArrayList<DiagnosisInnerData>()
    }

    var detailsData: SummaryPassModel? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiagnosisBinding.inflate(inflater)

        viewmodelBasicValid = ViewModelProvider(
            requireActivity(),
            BasicDetailValidationViewModelFactory(requireActivity())
        ).get(BasicDetailValidation::class.java)

        refereshArray = ArrayList()

        if (arguments?.getSerializable("Details") != null) {
            detailsData = arguments?.getSerializable("Details") as SummaryPassModel
        }

        Log.i("TAG", "onCreateViewCHECKDIAGNO: $dianosisChecked")
        val constDialog = Constant.getDialogCustom(requireContext())

        binding.btnSummary.setOnClickListener {
            Log.i("TAG", "onCreateViewCHECKDIAGNO: $dianosisChecked")
            dianosisChecked = false
            for (i in responseArrayData.indices) {
                Log.i(
                    "TAG",
                    "onCreateViewCHECKITEMSELECTED: ${responseArrayData.get(i).isItemChecked}"
                )
                if (responseArrayData.get(i).isItemChecked) {
                    dianosisChecked = true
                    break
                }
            }

            if (dianosisChecked) {
                // go to next step

                fun <T> List<T>.asArrayList(): ArrayList<T> =
                    if (this is ArrayList) this else ArrayList(this)

                if (detailsData != null) {
                    bundle.putSerializable("summaryDetails", detailsData)
                }
                for (i in responseArrayData) {
                    if (i.isItemChecked) {
                        listSelectedDianogsisId.add(i.id)
                    }
                }
                viewmodelBasicValid.diagnosisArray.postValue(responseArrayData)


                bundle.putParcelableArrayList("dianosisArray", responseArrayData)
                // bundle.putIntegerArrayList("dianosisArray",listSelectedDianogsisId.asArrayList())
                val summaryFrame = SummaryFragment()
                summaryFrame.arguments = bundle
                activity?.supportFragmentManager?.beginTransaction()
                    ?.add(R.id.frameDiagnosis, summaryFrame)?.commit()

                val viewpager = activity?.findViewById<ViewPager2>(R.id.viewpagerID)
                viewpager?.currentItem = 2

                //SummaryFragment().passDiagnosisArray(responseArrayData)

            } else {
                Constant.customDiagnosis(requireContext(),resources.getString(R.string.dianosischeckevalidation))
                for (i in responseArrayData.indices) {
                    responseArrayData.get(i).isNoItemChecked = true
                }
                adapterDiagnosis.notifyDataSetChanged()
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
            if (radioBtn.text.toString().equals(resources.getString(R.string.yesstr), true)) {
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

        viewModel.resultDiagnosisData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseHandler.Loading -> {
                    constDialog.show()
                }
                is ResponseHandler.OnFailed -> {
                    constDialog.cancel()
                }
                is ResponseHandler.OnSuccessResponse<ResponseData<List<DiagnosisDetailResponseModel>>?> -> {

                    constDialog.cancel()
                    Log.e("responsediagnosisarray=", responseArrayData.toString())
                    //responseArrayData.clear()

                    responseArrayData =
                        state.response?.data as ArrayList<DiagnosisDetailResponseModel>

                    binding.recyIdDiagnosis.layoutManager = LinearLayoutManager(requireContext())
                    adapterDiagnosis = DiagnosisAdapter(
                        responseArrayData,
                        { checkedState -> getDianosis(checkedState) },
                        resources.getString(R.string.bordergone)
                    )
                    binding.recyIdDiagnosis.adapter = adapterDiagnosis
                }
            }
        }

        return binding.root
    }

    private fun getDianosis(checkedState: Boolean) {
        dianosisChecked = checkedState
    }


}