package com.example.aris4autism_project.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.PastSubscriptionAdapter
import com.example.aris4autism_project.databinding.FragmentInfoOverViewBinding
import com.example.aris4autism_project.model.OverViewModel

class InfoOverViewFragment(val overViewData:OverViewModel) : Fragment() {

    lateinit var binding:FragmentInfoOverViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentInfoOverViewBinding.inflate(layoutInflater,container,false)

        binding.imgIdDetailIcon.setImageResource(overViewData.imgId)
        binding.txIdName.text=overViewData.name
        binding.txIdGender.text=overViewData.gen
        binding.IdYearly.text=overViewData.yearDetails
        binding.dobId.text=overViewData.dob
        binding.txidSubDetail.text="#"+overViewData.id.toString()
        binding.txidStartData.text=overViewData.startDob
        binding.txidStartData.text=overViewData.endDob

        if(overViewData.activeStatus)
        {
            binding.idActiveDetail.text="Active"
            binding.idPurchaseNewSub.visibility=View.GONE
        }
        else
        {
            binding.idActiveDetail.setBackgroundResource(R.drawable.status_expired_tag)
            binding.idActiveDetail.text="Expired"
            binding.idPurchaseNewSub.visibility=View.VISIBLE
        }

        if(!overViewData.pastSubscription.isEmpty())
        {
            Log.e("pastSub=",overViewData.pastSubscription.toString())
            binding.recyPastSub.layoutManager=LinearLayoutManager(requireActivity())
            binding.recyPastSub.adapter=PastSubscriptionAdapter(overViewData.pastSubscription)
            binding.idPastSubFound.visibility=View.GONE
            binding.recyPastSub.visibility=View.VISIBLE
        }
        else
        {
            binding.recyPastSub.visibility=View.GONE
            binding.idPastSubFound.visibility=View.VISIBLE
        }

        return binding.root

    }
}