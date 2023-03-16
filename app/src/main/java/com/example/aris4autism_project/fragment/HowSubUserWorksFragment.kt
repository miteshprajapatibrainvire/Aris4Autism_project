package com.example.aris4autism_project.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.adapter.VideoSubUserAd
import com.example.aris4autism_project.databinding.FragmentHowSubUserWorksBinding
import com.example.aris4autism_project.model.howitworkmodel.YoutubeVideoResponse
import com.example.aris4autism_project.model.responsemodel.ResponseData
import com.example.aris4autism_project.model.responsemodel.ResponseHandler
import com.example.aris4autism_project.viewmodel.HowItWorksViewModel
import com.example.aris4autism_project.viewmodel.HowItWorksViewModelFactory

class HowSubUserWorksFragment(s: String) : Fragment() {

    lateinit var binding:FragmentHowSubUserWorksBinding
    lateinit var viewModel:HowItWorksViewModel

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHowSubUserWorksBinding.inflate(inflater)

        viewModel=ViewModelProvider(requireActivity(),HowItWorksViewModelFactory(requireActivity())).get(HowItWorksViewModel::class.java)

        binding.recyIdsubUser.layoutManager=LinearLayoutManager(requireActivity())
//        binding.recyIdsubUser.adapter=VideoSubUserAd(lifecycle,HowItWorksMainFragment.mainYoutubeResponse.get(0).subusers)

        if(Utils.isOnline(requireContext())) {
            viewModel.getYoutubeVideosResponse()
        }
        else
        {
            Utils.InternetNotAvailableToast(requireContext())
        }

        viewModel.resultHowItWork.observe(viewLifecycleOwner,{
            state->
            when(state)
            {
                is ResponseHandler.Loading->{

                }
                is ResponseHandler.OnFailed->{

                }
                is ResponseHandler.OnSuccessResponse<ResponseData<YoutubeVideoResponse>?>->{
                    binding.recyIdsubUser.layoutManager=LinearLayoutManager(requireActivity())
                    binding.recyIdsubUser.adapter=VideoSubUserAd(lifecycle, state.response?.data!!.subusers)
                }

            }
        })
//        viewModel.resultHowItWork.observe(requireActivity(),{
//            when(it)
//            {
//                is BaseResponse.Success->{
//                    binding.recyIdsubUser.layoutManager=LinearLayoutManager(requireActivity())
//                    binding.recyIdsubUser.adapter=VideoSubUserAd(lifecycle,it.data!!.data)
//                }
//                is BaseResponse.Loading->{
//
//                }
//                is BaseResponse.Error->{
//                    Toast.makeText(requireContext(), it.msg.toString(), Toast.LENGTH_SHORT).show()
//                }
//            }
//        })

        return binding.root
    }


}