package com.example.aris4autism_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.adapter.DiagnosAdapter
import com.example.aris4autism_project.databinding.FragmentDiagnosisOverViewBinding
import com.example.aris4autism_project.model.DiagnosisModel
import com.example.aris4autism_project.model.GetDiagnosisData


class DiagnosisOverViewFragment(val diagnosModel:ArrayList<DiagnosisModel>) : Fragment() {

    lateinit var binding:FragmentDiagnosisOverViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentDiagnosisOverViewBinding.inflate(layoutInflater,container,false)

        binding.recyDiagnosis.adapter= DiagnosAdapter(diagnosModel as ArrayList<GetDiagnosisData>)
        binding.recyDiagnosis.layoutManager= LinearLayoutManager(requireActivity())


        return binding.root
    }
}