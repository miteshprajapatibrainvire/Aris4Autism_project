package com.example.aris4autism_project.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.adapter.MainAdapter
import com.example.aris4autism_project.adapter.ProfileAdapter
import com.example.aris4autism_project.databinding.FragmentAddNewLearnerBinding
import com.example.aris4autism_project.model.BundleModel
import com.example.aris4autism_project.model.GetDiagnosisData
import com.example.aris4autism_project.viewmodel.LearnerViewModel
import com.example.aris4autism_project.viewmodel.LearnerViewModelFactory
import com.example.aris4autism_project.viewmodel.ProfileDetailViewModel
import com.example.aris4autism_project.viewmodel.ProfileDetailViewModelFactory
import nl.isaac.android.StepIndicator

@Suppress("UNCHECKED_CAST")
class AddNewLearnerFragment : Fragment() {

    lateinit var binding: FragmentAddNewLearnerBinding
    var bundleModel=BundleModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNewLearnerBinding.inflate(inflater)
        var bundle = Bundle()

        if(requireArguments().getString("BundleState").toString().equals("activeStateBundle",true)) {
            bundleModel=BundleModel()
            bundle=Bundle()
            Log.e("data=","BundleState")
        }
        else{
            Log.e("State=","sendData")
            BundleModel(
                requireArguments().getString("uuid").toString(),
                requireArguments().getString("name").toString(),
                requireArguments().getString("gender").toString(),
                requireArguments().getString("dob").toString(),
                requireArguments().getString("monthlyplan").toString(),
                requireArguments().getString("activeStatus").toString(),
                requireArguments().getString("startDob").toString(),
                requireArguments().getString("endDob").toString(),
                requireArguments().getSerializable("diagnotsisArray")!! as ArrayList<GetDiagnosisData>,
                requireArguments().getString("subscriptionId").toString(),
                requireArguments().getString("iconImg").toString()
            ).also { bundleModel = it }
            bundle.putString("uuid", requireArguments().getString("uuid").toString())
            bundle.putString("name", requireArguments().getString("name").toString())
            bundle.putString("gender", requireArguments().getString("gender").toString())
            bundle.putString("dob", requireArguments().getString("dob").toString())
            bundle.putString("monthlyplan", requireArguments().getString("monthlyplan").toString())
            bundle.putString(
                "activeStatus",
                requireArguments().getString("activeStatus").toString()
            )
            bundle.putString("startDob", requireArguments().getString("startDob").toString())
            bundle.putString("endDob", requireArguments().getString("endDob").toString())
            bundle.putSerializable(
                "diagnotsisArray",
                requireArguments().getSerializable("diagnotsisArray") as ArrayList<GetDiagnosisData>
            )
            bundle.putString(
                "subscriptionId",
                requireArguments().getString("subscriptionId").toString()
            )
            bundle.putString("iconImg", requireArguments().getString("iconImg").toString())
            Log.e("bundlePass=", bundleModel.toString())
        }


        val mainViewPager = MainAdapter(requireActivity())
        mainViewPager.addFragment(BasicDetailsFragment(bundleModel), "Basic Details")
        mainViewPager.addFragment(DiagnosisFragment(), "Diagnosis")
        mainViewPager.addFragment(SummaryFragment(), "Summary")

        binding.viewpagerID.adapter = mainViewPager
        binding.idLayoutToolbar.imgHeart.setOnClickListener {

            if(requireArguments().getString("BundleState").toString().equals("activeStateBundle",true)) {
                findNavController().navigate(R.id.action_addNewLearnerFragment_to_learnersFragment2)
                Log.e("data=","BundleState")
            }
            else
            {
                Log.e("data=","SendData")
                findNavController().navigate(
                    R.id.action_addNewLearnerFragment_to_learnerDetailsFragment,
                    bundle
                )
            }

        }

        //backpress  from learnerleatails fragment to learner fragment
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                if(requireArguments().getString("BundleState").toString().equals("activeStateBundle",true))
                {
                    findNavController().navigate(R.id.action_addNewLearnerFragment_to_learnersFragment2)
                    Log.e("data=","BundleState")
                }
                else
                {
                    Log.e("data=","SendData")
                    findNavController().navigate(
                        R.id.action_addNewLearnerFragment_to_learnerDetailsFragment,
                        bundle
                    )
                }

            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        binding.viewpagerID.isUserInputEnabled = false
        val stepIndicatorIcons: StepIndicator =
            binding.idLayoutToolbar.stepIndicatorNumbersAddnewLearner

        stepIndicatorIcons.apply {

            setupWithViewPager(binding.viewpagerID)
            showLabels = true
            labels = listOf("Basic Details", "Diagnosis", "Summary")
            fillNextStep = false
            fillPreviousStep = true
            activeStepColor = ContextCompat.getColor(context, R.color.darkblue)
            previousStepColor = ContextCompat.getColor(context, R.color.darkblue)
            activeStepColor = ContextCompat.getColor(context, R.color.darkblue)
            previousStepLabelColor = ContextCompat.getColor(context, R.color.gray)
            activeStepLabelColor = ContextCompat.getColor(context, R.color.darkblue)
            activeStepIndicatorTypeColor = ContextCompat.getColor(context, R.color.darkblue)
            previousStepIndicatorTypeColor = ContextCompat.getColor(context, R.color.white)
            nextStepIndicatorTypeColor = ContextCompat.getColor(context, R.color.darkblue)
            activeStepPosition=ContextCompat.getColor(context,R.color.darkblue)

        }
        return binding.root
    }


}