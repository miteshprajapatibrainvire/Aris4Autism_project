package com.example.aris4autism_project.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.DiagnosAdapter
import com.example.aris4autism_project.databinding.FragmentLearnerDetailsBinding
import com.example.aris4autism_project.model.GetDiagnosisData

class LearnerDetailsFragment : Fragment() {

    lateinit var binding:FragmentLearnerDetailsBinding
    private lateinit var navController: NavController
    lateinit var includeData:View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding=FragmentLearnerDetailsBinding.inflate(layoutInflater, container, false)
        val view=requireActivity().findViewById<View>(R.id.idDataLayout)
        view.visibility=View.GONE

        binding.mainLayoutId.txIdMainLabel.text="LEARNER'S DETAILS"

//        val view= inflater.inflate(R.layout.fragment_learner_details, container, false)
        val name:String = requireArguments().getString("name").toString()
        val gender:String = requireArguments().getString("gender").toString()
        val yearDetail:String=requireArguments().getString("age").toString()
        val dob:String=requireArguments().getString("dob").toString()
        val monthlyplan=requireArguments().getString("monthlyplan").toString()
        val startToEnd=requireArguments().getString("starttoenddob").toString()
        val activeStatus:String=requireArguments().getString("activeStatus").toString()
        val startDob=requireArguments().getString("startDob").toString()
        val endDob=requireArguments().getString("endDob").toString()
        val diagnosis=requireArguments().getSerializable("diagnotsisArray")
        val subId=requireArguments().getString("subscriptionId").toString()
        val imgIcon:String= requireArguments().getString("iconImg").toString()
        Log.e("iconImg",imgIcon.toString())

        Log.e("dataFragment=",name+"="+gender+"="+yearDetail+"="+dob+"="+monthlyplan+"="+startToEnd+"="+
        activeStatus.toString()+"="+startDob+"="+endDob+"="+diagnosis.toString())
        Log.e("status=",activeStatus.toString())

        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        binding.recyDiagnosis.adapter=DiagnosAdapter(diagnosis as ArrayList<GetDiagnosisData>)
        binding.recyDiagnosis.layoutManager= LinearLayoutManager(requireActivity())
        binding.txIdName.text=name
        binding.txIdGender.text=gender
        binding.IdYearly.text=yearDetail
        binding.dobId.text="DOB : "+dob
        binding.txidStartData.text=startDob
        binding.txidEndData.text=endDob
        binding.txMonthPlan.text=monthlyplan
        binding.txidSubDetail.text="#"+subId

        Log.e("imageIcon=",imgIcon.toString())
        Glide.with(requireActivity())
            .load(imgIcon)
            .into(binding.imgIdDetailIcon)
       // binding.imgIdDetailIcon.setImageResource(imgIcon)

        binding.mainLayoutId.imgMainBack.setOnClickListener {
            findNavController().navigate(R.id.action_learnerDetailsFragment_to_learnersFragment2)
        }

        val callback=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_learnerDetailsFragment_to_learnersFragment2)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        if(!activeStatus.equals("active"))
        {
            binding.idActiveDetail.setBackgroundResource(R.drawable.status_expired_tag)
            binding.idActiveDetail.text="Expired"
            binding.idPurchaseNewSub.visibility=View.VISIBLE
        }
        else
        {
            binding.idPurchaseNewSub.visibility=View.GONE
        }

        return binding.root
    }

}