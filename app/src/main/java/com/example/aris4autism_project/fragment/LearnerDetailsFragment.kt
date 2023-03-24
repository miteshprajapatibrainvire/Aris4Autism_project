@file:Suppress("UNCHECKED_CAST")

package com.example.aris4autism_project.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.adapter.DiagnosAdapter
import com.example.aris4autism_project.databinding.FragmentLearnerDetailsBinding
import com.example.aris4autism_project.model.editlearnermodel.SingleUserEditLearnerModel
import com.example.aris4autism_project.model.learnermodel.LearnerData
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.viewmodel.LearnerViewModel
import com.example.aris4autism_project.viewmodel.LearnerViewModelFactory


@Suppress("DEPRECATION", "NAME_SHADOWING")
class LearnerDetailsFragment : Fragment() {

    lateinit var binding:FragmentLearnerDetailsBinding
    private lateinit var navController: NavController
    lateinit var viewModelLearner: LearnerViewModel
//     private val args : LearnerDetailsFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding=FragmentLearnerDetailsBinding.inflate(layoutInflater, container, false)

        //set visibility main toolbar
        val view=requireActivity().findViewById<View>(R.id.idDataLayout)
        view.visibility=View.GONE

        val constDialog = Constant.getDialogCustom(requireContext())

        viewModelLearner =
            ViewModelProvider(requireActivity(), LearnerViewModelFactory(requireContext())).get(
                LearnerViewModel::class.java
            )
        //set main toolbar text
        binding.mainLayoutId.txIdMainLabel.text = resources.getString(R.string.learnerdetail)


        viewModelLearner.getEditLearnerResponse(requireArguments().getString(resources.getString(R.string.uuid)).toString())

        viewModelLearner.resultEditLearner.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseHandler.Loading -> {
                    constDialog.show()
                }

                is ResponseHandler.OnFailed -> {
                    constDialog.cancel()
                }

                is ResponseHandler.OnSuccessResponse<ResponseData<LearnerData>?> -> {
                    constDialog.cancel()

                    state.response?.data?.let {
                        binding.learnerModel = it
                    }

                    state.response.let{
                        Glide.with(requireActivity())
                            .load(it?.data?.getLearnerIcon?.iconUrl)
                            .into(binding.imgIdDetailIcon)
                    }

                    /*binding.name=state.response.data?.name
                    binding.gender=state.response.data?.gender
                    binding.dobtoAge=Utils.dobToAge(state.response.data?.dateOfBirth.toString())
                    binding.dob="DOB :"+state.response.data?.dateOfBirth
                    binding.monthlyplan=state.response.data?.userSubscriptions?.title
                    Log.e("active=",state.response.data?.userSubscriptions?.status.toString())
                    binding.startDate=state.response.data?.userSubscriptions?.startDate
                    binding.endDate=state.response.data?.userSubscriptions?.endDate
                    binding.subscriptionId="#"+state.response.data?.subscriptionId.toString()
                    if(state.response.data?.userSubscriptions?.status.equals("active",true))
                    {
                        binding.activeStatusBackground=true
                        binding.active="Active"
                    }
                    if(state.response.data?.userSubscriptions==null)
                    {
                        binding.active="Expired"
                        binding.activeStatusBackground=false
                    }*/
                    binding.recyDiagnosis.adapter=DiagnosAdapter(state.response?.data?.getDiagnosisData)
                    binding.recyDiagnosis.layoutManager= LinearLayoutManager(requireActivity())

                }
            }
        }

        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        val bundle=Bundle()
        bundle.putString(resources.getString(R.string.uuid),requireArguments().getString(resources.getString(R.string.uuid)).toString())
        binding.mainLayoutId.idDetailPerson.setOnClickListener{

            bundle.putString(resources.getString(R.string.uuid),requireArguments().getString(resources.getString(R.string.uuid)).toString())
            bundle.putString(resources.getString(R.string.iconImg),requireArguments().getString(resources.getString(R.string.iconImg)).toString())
            bundle.putString("iconImgId",requireArguments().getString("iconImgId").toString())
            bundle.putString("subscriptionId",binding.txidSubDetail.text.toString())
            Log.i("TAG", "onCreateView_LearnerDetails: ${bundle.toString()}")
            findNavController().navigate(R.id.action_learnerDetailsFragment_to_addNewLearnerFragment,bundle)

        }

        //navigate learnerdetailfragment to learnerfragment
        binding.mainLayoutId.imgMainBack.setOnClickListener {
            findNavController().navigate(R.id.action_learnerDetailsFragment_to_learnersFragment2)
        }

        //backpress  from learnerleatails fragment to learner fragment
        val callback=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_learnerDetailsFragment_to_learnersFragment2)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)


        return binding.root
    }


}