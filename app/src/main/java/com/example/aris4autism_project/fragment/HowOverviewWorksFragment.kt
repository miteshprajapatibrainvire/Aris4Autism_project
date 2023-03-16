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
import com.example.aris4autism_project.adapter.VideoOverViewAdapter
import com.example.aris4autism_project.databinding.FragmentHowOverviewWorksBinding
import com.example.aris4autism_project.model.howitworkmodel.VideoOverview
import com.example.aris4autism_project.model.howitworkmodel.YoutubeVideoResponseModel
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.viewmodel.HowItWorksViewModel
import com.example.aris4autism_project.viewmodel.HowItWorksViewModelFactory

class HowOverviewWorksFragment(s: String) : Fragment() {

    lateinit var binding:FragmentHowOverviewWorksBinding
    companion object{
        var overviewWorkArray:ArrayList<VideoOverview> = ArrayList<VideoOverview>()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHowOverviewWorksBinding.inflate(inflater)



                    binding.recyIdOverview.layoutManager=LinearLayoutManager(requireActivity())
                    binding.recyIdOverview.adapter=VideoOverViewAdapter(lifecycle,
                        overviewWorkArray)

        return binding.root
    }


}