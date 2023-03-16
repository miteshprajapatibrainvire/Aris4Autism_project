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
import com.example.aris4autism_project.model.howitworkmodel.VideoSubuser
import com.example.aris4autism_project.model.howitworkmodel.YoutubeVideoResponseModel
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.viewmodel.HowItWorksViewModel
import com.example.aris4autism_project.viewmodel.HowItWorksViewModelFactory

class HowSubUserWorksFragment(s: String) : Fragment() {

    lateinit var binding:FragmentHowSubUserWorksBinding
    companion object{
        var subuserWorkArray:ArrayList<VideoSubuser> = ArrayList<VideoSubuser>()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHowSubUserWorksBinding.inflate(inflater)


              binding.recyIdsubUser.layoutManager=LinearLayoutManager(requireActivity())
                    binding.recyIdsubUser.adapter=VideoSubUserAd(lifecycle,subuserWorkArray)


        return binding.root
    }


}