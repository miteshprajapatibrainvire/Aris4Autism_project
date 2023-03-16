package com.example.aris4autism_project.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.adapter.DiagnosAdapter
import com.example.aris4autism_project.databinding.FragmentDiagnosisOverViewBinding
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.viewmodel.OverViewViewModel
import com.example.aris4autism_project.viewmodel.OverViewViewModelFactory
import com.example.food_nutrition_recipe_app.model.clonemodel.OverViewInnerDetailResponse


class DiagnosisOverViewFragment() : Fragment() {

    lateinit var binding: FragmentDiagnosisOverViewBinding
    lateinit var viewModel: OverViewViewModel

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiagnosisOverViewBinding.inflate(layoutInflater, container, false)

        //call dialogbox
        val constDialog = Constant.getDialogCustom(requireContext())

        //call viewmodel for fetch api
        viewModel =
            ViewModelProvider(requireActivity(), OverViewViewModelFactory(requireActivity())).get(
                OverViewViewModel::class.java
            )
        if (Utils.isOnline(requireContext())) {
            viewModel.getOverViewInnerDetails(
                Constant.editUserId
            )
        } else {
            Utils.InternetNotAvailableToast(requireContext())
        }

        //call viewmodel observer get api response
        viewModel.resultInnerOverView.observe(viewLifecycleOwner,{
            state->
            when(state)
            {
                 is ResponseHandler.Loading->{
                     constDialog.show()
                 }
                 is ResponseHandler.OnFailed->{
                     constDialog.cancel()
                 }
                is ResponseHandler.OnSuccessResponse<ResponseData<OverViewInnerDetailResponse>?>->{
                    Log.e("response=",state.response?.data.toString())
                    constDialog.cancel()
                    state.response?.data?.get_diagnosis_data.let { GetDiagnosisData ->
                        binding.recyDiagnosis.adapter =
                            GetDiagnosisData?.let { it1 -> DiagnosAdapter(it1) }
                    }
                    binding.recyDiagnosis.layoutManager = LinearLayoutManager(requireActivity())
                }
            }
        })

        return binding.root
    }

}