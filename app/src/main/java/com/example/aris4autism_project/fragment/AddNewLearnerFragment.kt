package com.example.aris4autism_project.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.MainAdapter
import com.example.aris4autism_project.databinding.FragmentAddNewLearnerBinding
import com.example.aris4autism_project.model.BundleModel
import com.example.aris4autism_project.model.learnermodel.LearnerDiagnosisData
import nl.isaac.android.StepIndicator

@Suppress("UNCHECKED_CAST", "DEPRECATION")
class AddNewLearnerFragment : Fragment() {

    lateinit var binding: FragmentAddNewLearnerBinding
    var bundleModel = BundleModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNewLearnerBinding.inflate(inflater)
        var bundle = Bundle()

        if (requireArguments().getString(resources.getString(R.string.bundleCast)).toString()
                .equals(resources.getString(R.string.activeBundle), true)
        ) {
            bundleModel = BundleModel()
            bundle = Bundle()
        } else {
            Log.e("iconImgIdAddLearner=", requireArguments().getString("iconImgId").toString())
            bundleModel= BundleModel()

            BundleModel(
                requireArguments().getString("iconImgId").toString(),
                requireArguments().getString(resources.getString(R.string.uuid)).toString(),
                requireArguments().getString(resources.getString(R.string.iconImg)).toString(),
                requireArguments().getString(getString(R.string.subId)).toString()
            ).also { bundleModel = it }

            bundle.putString(
                resources.getString(R.string.uuid),
                requireArguments().getString(resources.getString(R.string.uuid)).toString()
            )

            bundle.putString(
                resources.getString(R.string.iconImg),
                requireArguments().getString(resources.getString(R.string.iconImg)).toString()
            )

        }

        val mainViewPager = MainAdapter(requireActivity())
        mainViewPager.addFragment(
            BasicDetailsFragment(bundleModel),
            resources.getString(R.string.basicdetails)
        )

        mainViewPager.addFragment(DiagnosisFragment(), resources.getString(R.string.diagnosisdata))
        mainViewPager.addFragment(SummaryFragment(), resources.getString(R.string.summary))

        binding.viewpagerID.adapter = mainViewPager
        binding.idLayoutToolbar.imgHeart.setOnClickListener {
            DiagnosisFragment.diagnosisArray.clear()
            if (requireArguments().getString(resources.getString(R.string.bundlestate)).toString()
                    .equals(resources.getString(R.string.activeStateBundle), true)
            ) {
                findNavController().navigate(R.id.action_addNewLearnerFragment_to_learnersFragment2)
            } else {
                findNavController().navigate(
                    R.id.action_addNewLearnerFragment_to_learnerDetailsFragment,
                    bundle
                )
            }
        }

        //backpress  from learnerleatails fragment to learner fragment
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                DiagnosisFragment.diagnosisArray.clear()

                if (requireArguments().getString(resources.getString(R.string.bundlestate)).toString()
                        .equals(resources.getString(R.string.activeStateBundle), true)
                ) {
                    findNavController().navigate(R.id.action_addNewLearnerFragment_to_learnersFragment2)
                } else {
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
            labels = listOf(resources.getString(R.string.basicdetails), resources.getString(R.string.diagnosisdata), resources.getString(R.string.summary))
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
            activeStepPosition = ContextCompat.getColor(context, R.color.darkblue)

        }
        return binding.root
    }


}