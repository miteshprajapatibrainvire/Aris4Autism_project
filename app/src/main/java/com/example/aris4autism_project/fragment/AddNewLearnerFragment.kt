package com.example.aris4autism_project.fragment

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.adapter.MainAdapter
import com.example.aris4autism_project.adapter.ProfileAdapter
import com.example.aris4autism_project.databinding.FragmentAddNewLearnerBinding
import com.example.aris4autism_project.viewmodel.LearnerViewModel
import com.example.aris4autism_project.viewmodel.LearnerViewModelFactory
import com.example.aris4autism_project.viewmodel.ProfileDetailViewModel
import com.example.aris4autism_project.viewmodel.ProfileDetailViewModelFactory
import nl.isaac.android.StepIndicator
import kotlin.time.days

class AddNewLearnerFragment : Fragment() {

    lateinit var binding:FragmentAddNewLearnerBinding

    lateinit var viewModel:ProfileDetailViewModel
    lateinit var viewModelLearner:LearnerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        binding=FragmentAddNewLearnerBinding.inflate(inflater)

        val viewpager=MainAdapter(requireActivity())
        viewpager.addFragment(BasicDetailsFragment(),"Basic Details")
        viewpager.addFragment(DiagnosisFragment(),"Diagnosis")
        viewpager.addFragment(SummaryFragment(),"Summary")
//      binding.idViewpagerAddNewLearner.offscreenPageLimit=2

        val uuiddata:String=requireArguments().getString("uuid").toString()
        val name:String = requireArguments().getString("name").toString()
        val gender:String = requireArguments().getString("gender").toString()
        val dob:String=requireArguments().getString("dob").toString()
        val monthlyplan=requireArguments().getString("monthlyplan").toString()
        val activeStatus:String=requireArguments().getString("activeStatus").toString()
        val startDob=requireArguments().getString("startDob").toString()
        val endDob=requireArguments().getString("endDob").toString()
        val diagnosis=requireArguments().getSerializable("diagnotsisArray")
        val subId=requireArguments().getString("subscriptionId").toString()
        val imgIcon:String= requireArguments().getString("iconImg").toString()

        val uuid:String=requireArguments().getString("uuid").toString()

        val bundle=Bundle()
        bundle.putString("uuid",requireArguments().getString("uuid").toString())
        bundle.putString("name",requireArguments().getString("name").toString())
        bundle.putString("gender",requireArguments().getString("gender").toString())
        bundle.putString("dob",requireArguments().getString("dob").toString())
        bundle.putString("monthlyplan",requireArguments().getString("monthlyplan").toString())
        bundle.putString("activeStatus",requireArguments().getString("activeStatus").toString())
        bundle.putString("startDob",requireArguments().getString("startDob").toString())
        bundle.putString("endDob",requireArguments().getString("endDob").toString())
        bundle.putSerializable("diagnotsisArray",requireArguments().getSerializable("diagnotsisArray"))
        bundle.putString("subscriptionId",requireArguments().getString("subscriptionId").toString())
        bundle.putString("iconImg",requireArguments().getString("iconImg").toString())


        binding.idLayoutToolbar.imgHeart.setOnClickListener {
            findNavController().navigate(R.id.action_addNewLearnerFragment_to_learnerDetailsFragment,bundle)
        }

        val constDialog=Constant.getDialogCustom(requireContext())
        viewModelLearner=ViewModelProvider(requireActivity(),LearnerViewModelFactory(requireContext())).get(LearnerViewModel::class.java)
        Log.e("=uuid=",uuid.toString())
        viewModelLearner.getEditLearnerResponse(uuiddata,"eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWVkNWJhMDhkNmQwMTYyMDcyYTYwNzg4NTRiOTQwNjE2M2Q4NTkyMzRiMGMyOTA5NWFjOWIyMDE1MGQzYWMzZmFiNzdkZDQ0MDMzMGQzZWQiLCJpYXQiOjE2NzU3NTA1MDAsIm5iZiI6MTY3NTc1MDUwMCwiZXhwIjoxNzA3Mjg2NTAwLCJzdWIiOiI5MzMiLCJzY29wZXMiOltdfQ.D_YETTNEt8ZehNHmU15bY5IAPy8QTC3ZV9YzhIrX3BZC2C6YV6W1QjYF5NfnIttEb7dqD-kpWn9llGnk7mIw29hmfdmfUN0yQeN2SPSMQgQdcoauqLfQAktU9nn5D6MyBVHgwA9iI5NvxoyrodWZ4zp6G_SEuGUzmVpSEdcPccKnlHtPHmsGhEcahngaIrF0tPfLrB0AuCXhmb1p9rJNnCkfoCvK-R81E_dFR5pzm6z0jMm0rEExd0kjkvtrVfls8laKxR17JHP9gx4Qgm1P-9gMtfHPt4VqTq57QHYjoxFkog3btw6Qq7QizwkDJnIuAJYw6kHz1UDsyYXXhmVLhctaBLirzJxbT7tdy0W-ByOfu9okXv9CTnIREAbFBbopdoL0L0jF7TXx_8l6V0RBuZEsoQ8d0ohPRE7dTU3clKApA50zEqTTehQTHG-Ghzn97pO8lY5d2ti5xO1GS1lopKuSYP1WdiLd5clQ51EPDbed9CMT4k8fqVyZHOonq_ITAexDMl_mHB3rpPFM4MfpWbx3jVsaUSbxLvK-hpufggIJlEsRgSD8yZIA8wUqfGzcbbtVbf1omiKa-1sopcjcW36q48gY-ZM3RHH8-KA98P0AgkjPTtlKGOMIpbDNCaduuc3F5qbID8cpzFPkEj0VGL45EsIIaYuZI5WjwTXFRVE","Android","1")
        viewModelLearner.resultEditLearner.observe(requireActivity(),
            {
                when(it)
                {
                    is BaseResponse.Success->
                    {
                        Log.e("EditLearner=",it.data!!.data.toString())
                        binding.idfullNameEd.setText(it.data!!.data.name.toString())
                        binding.idGenEd.setText(it.data!!.data.gender)
                        binding.idDobEd.setText(it.data!!.data.dateOfBirth)
                        binding.idtxmonthtitle.setText(it.data!!.data.userSubscriptions.title)
                        binding.idtxSubscriptionId.setText("#"+it.data!!.data.subscriptionId.toString())
                        binding.idtxstartdate.setText(it.data!!.data.userSubscriptions.startDate)
                        binding.idtxenddate.setText(it.data!!.data.userSubscriptions.endDate)
                        constDialog.cancel()
                    }
                    is BaseResponse.Error->{
                        constDialog.cancel()
                    }
                    is BaseResponse.Loading->{
                        constDialog.show()
                    }
                }
        })

        viewModel=ViewModelProvider(requireActivity(),ProfileDetailViewModelFactory(requireContext())).get(ProfileDetailViewModel::class.java)

        viewModel.getUserProfileIconDetail("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWVkNWJhMDhkNmQwMTYyMDcyYTYwNzg4NTRiOTQwNjE2M2Q4NTkyMzRiMGMyOTA5NWFjOWIyMDE1MGQzYWMzZmFiNzdkZDQ0MDMzMGQzZWQiLCJpYXQiOjE2NzU3NTA1MDAsIm5iZiI6MTY3NTc1MDUwMCwiZXhwIjoxNzA3Mjg2NTAwLCJzdWIiOiI5MzMiLCJzY29wZXMiOltdfQ.D_YETTNEt8ZehNHmU15bY5IAPy8QTC3ZV9YzhIrX3BZC2C6YV6W1QjYF5NfnIttEb7dqD-kpWn9llGnk7mIw29hmfdmfUN0yQeN2SPSMQgQdcoauqLfQAktU9nn5D6MyBVHgwA9iI5NvxoyrodWZ4zp6G_SEuGUzmVpSEdcPccKnlHtPHmsGhEcahngaIrF0tPfLrB0AuCXhmb1p9rJNnCkfoCvK-R81E_dFR5pzm6z0jMm0rEExd0kjkvtrVfls8laKxR17JHP9gx4Qgm1P-9gMtfHPt4VqTq57QHYjoxFkog3btw6Qq7QizwkDJnIuAJYw6kHz1UDsyYXXhmVLhctaBLirzJxbT7tdy0W-ByOfu9okXv9CTnIREAbFBbopdoL0L0jF7TXx_8l6V0RBuZEsoQ8d0ohPRE7dTU3clKApA50zEqTTehQTHG-Ghzn97pO8lY5d2ti5xO1GS1lopKuSYP1WdiLd5clQ51EPDbed9CMT4k8fqVyZHOonq_ITAexDMl_mHB3rpPFM4MfpWbx3jVsaUSbxLvK-hpufggIJlEsRgSD8yZIA8wUqfGzcbbtVbf1omiKa-1sopcjcW36q48gY-ZM3RHH8-KA98P0AgkjPTtlKGOMIpbDNCaduuc3F5qbID8cpzFPkEj0VGL45EsIIaYuZI5WjwTXFRVE","Android","1")

        viewModel.resultProfileIcon.observe(requireActivity(),{

            when(it)
            {
                is BaseResponse.Success->
                {
                    Log.e("ResponseAddNewLearner=",it.data!!.data.original.toString())
                    binding.recyAddnewlearnerIcon.layoutManager=GridLayoutManager(requireActivity(),4)
                    binding.recyAddnewlearnerIcon.adapter=ProfileAdapter(it.data!!.data.original.data)
                }

                is BaseResponse.Error->{

                }

                is BaseResponse.Loading->{

                }
            }
        })

        //backpress  from learnerleatails fragment to learner fragment
        val callback=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
               findNavController().navigate(R.id.action_addNewLearnerFragment_to_learnerDetailsFragment,bundle)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

            val viewAdapter= MainAdapter(activity)
            viewAdapter.addFragment(BasicDetailsFragment(),"Basic Details")
            viewAdapter.addFragment(DiagnosisFragment(),"Diagnosis")
            viewAdapter.addFragment(SummaryFragment(),"Summary")

        val stepIndicatorIcons: StepIndicator = binding.idLayoutToolbar.stepIndicatorNumbersAddnewLearner

        stepIndicatorIcons.apply {

            //setupWithViewPager(binding.registerViewPager)
            showLabels = true
            labels = listOf("Basic Details", "Diagnosis","Summary")
            fillNextStep = false
            fillPreviousStep = true
            activeStepColor= ContextCompat.getColor(context,R.color.darkblue)
            previousStepColor = ContextCompat.getColor(context, R.color.darkblue)
            activeStepColor = ContextCompat.getColor(context, R.color.darkblue)
            previousStepLabelColor = ContextCompat.getColor(context, R.color.darkblue)
            activeStepLabelColor = ContextCompat.getColor(context, R.color.darkblue)
            activeStepIndicatorTypeColor = ContextCompat.getColor(context, R.color.darkblue)
            previousStepIndicatorTypeColor = ContextCompat.getColor(context, R.color.white)
            nextStepIndicatorTypeColor = ContextCompat.getColor(context, R.color.darkblue)

        }

        return binding.root
    }


}