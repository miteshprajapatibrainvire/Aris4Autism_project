package com.example.aris4autism_project.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.TabAdapter
import com.example.aris4autism_project.databinding.FragmentHowItWorksMainBinding
import com.example.aris4autism_project.model.DataXXXXXXXXXXXXXXXXXXXXXXXX
import com.example.aris4autism_project.model.howitworkmodel.VideoLearners
import com.example.aris4autism_project.model.howitworkmodel.YoutubeVideoResponse
import com.google.android.material.tabs.TabLayout

class HowItWorksMainFragment : Fragment() {

    lateinit var binding:FragmentHowItWorksMainBinding
    //lateinit var viewmodel:HowItWorksViewModel
    var mainArrayList:ArrayList<DataXXXXXXXXXXXXXXXXXXXXXXXX> ?=null
    var mainLearnerWork:ArrayList<VideoLearners> ?= null
    var bundle=Bundle()
    companion object{
        var mainYoutubeResponse=ArrayList<YoutubeVideoResponse>()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        binding=FragmentHowItWorksMainBinding.inflate(inflater)
        mainArrayList=ArrayList()
        mainLearnerWork= ArrayList()

        binding.topToolbarHowitWork.txIdMainLabel.text=resources.getString(R.string.howitwork)
        binding.topToolbarHowitWork.idDetailPerson.visibility=View.GONE
        binding.topToolbarHowitWork.imgMainBack.setOnClickListener {
            findNavController().navigate(R.id.action_howItWorksMainFragment_to_learnersFragment2)
        }

       // viewmodel=ViewModelProvider(requireActivity(),HowItWorksViewModelFactory(requireActivity())).get(HowItWorksViewModel::class.java)

       /* if(Utils.isOnline(requireContext())) {
            viewmodel.getYoutubeVideosResponse()
        }
        else{
            Utils.InternetNotAvailableToast(requireContext())
        }

        viewmodel.resultHowItWork.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseHandler.Loading -> {

                }
                is ResponseHandler.OnFailed -> {

                }
                is ResponseHandler.OnSuccessResponse<ResponseData<YoutubeVideoResponse>?> -> {
                    Log.e("howitworkResponseModel=", state.response?.data?.learners.toString())
                    mainYoutubeResponse.add(state.response?.data!!)
                    Log.e("responseData=", mainYoutubeResponse.toString())
                }
            }
        }*/

        /* viewmodel.resultHowItWork.observe(requireActivity(),{
             when(it)
             {
                 is BaseResponse.Success->
                 {
                     mainArrayList!!.add(it.data!!.data)
                     mainLearnerWork!!.addAll(it.data.data.learners)
 //                    bundle.putString("passData","jsonData")
 //                    bundle.putSerializable("bundleArrayLearner",it.data.data.learners)
                 }

                 is BaseResponse.Loading->{
                 }

                 is BaseResponse.Error->{
                     Toast.makeText(requireContext(), it.msg.toString(), Toast.LENGTH_SHORT).show()
                 }
             }
         })*/

        binding.tabLayoutHowitWork.addTab(binding.tabLayoutHowitWork.newTab().setText("Learners"))
        binding.tabLayoutHowitWork.addTab(binding.tabLayoutHowitWork.newTab().setText("Subuser"))
        binding.tabLayoutHowitWork.addTab(binding.tabLayoutHowitWork.newTab().setText("Overview"))
        binding.tabViewpager.setUserInputEnabled(false)
        binding.tabLayoutHowitWork.tabGravity = TabLayout.GRAVITY_FILL
        val tabAdapter=TabAdapter(requireActivity())
        tabAdapter.addFragment(HowLearnerWorksFragment("learner"),"Learners")
        tabAdapter.addFragment(HowSubUserWorksFragment("subuser"),"Subuser")
        tabAdapter.addFragment(HowOverviewWorksFragment("overview"),"Overview")

        binding.tabViewpager.adapter=tabAdapter
        binding.tabViewpager.offscreenPageLimit = 2

        try {

            var dataString = requireArguments().getString("howWork")

//        Log.e("StringRecord=",dataString.toString())
            if (dataString.toString().equals("learner", true)) {
                binding.tabViewpager.currentItem = 0
                binding.tabLayoutHowitWork.getTabAt(0)?.select()
                Log.e("learner=", dataString.toString())
            }

            if (dataString.toString().equals("subuser", true)) {
                binding.tabViewpager.currentItem = 1
                binding.tabLayoutHowitWork.getTabAt(1)?.select()
                Log.e("subser=", dataString.toString())
            }

            if (dataString.toString().equals("overview", true)) {
                binding.tabViewpager.currentItem = 2
                binding.tabLayoutHowitWork.getTabAt(2)?.select()
                Log.e("overview=", dataString.toString())
            }
        }
        catch(e:Exception)
        {
            Log.e("Exception=",e.toString())
        }


//        var callback=object : OnBackPressedCallback(true){
//            override fun handleOnBackPressed() {
//                findNavController().navigate(R.id.howItWorksMainFragment)
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(callback)

        binding.tabLayoutHowitWork.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab)
            {
                binding.tabViewpager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}

        })

        return binding.root
    }
}