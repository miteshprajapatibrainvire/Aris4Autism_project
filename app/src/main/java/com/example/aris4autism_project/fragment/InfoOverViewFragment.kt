package com.example.aris4autism_project.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.databinding.FragmentInfoOverViewBinding
import com.example.aris4autism_project.viewmodel.OverViewViewModel
import com.example.aris4autism_project.viewmodel.OverViewViewModelFactory
import com.google.gson.reflect.TypeToken


class InfoOverViewFragment(val overViewData: OverViewData) : Fragment() {

    lateinit var binding:FragmentInfoOverViewBinding
    lateinit var viewModel: OverViewViewModel

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentInfoOverViewBinding.inflate(inflater)

        //call dialog box
        val const=Constant.getDialogCustom(requireContext())

        //glide library for fetch image
        Glide.with(requireActivity())
            .load(overViewData.getLearnerIcon.iconUrl)
            .into(binding.imgIdDetailIcon)

        // val typeToken=object : TypeToken<OverViewData>(){}.type

        //initialize viewmodel
        viewModel=ViewModelProvider(requireActivity(),OverViewViewModelFactory(requireActivity())).get(OverViewViewModel::class.java)

        if(Utils.isOnline(requireContext())) {
            //call api
            viewModel.getOverViewInnerDetails(
                overViewData.uuid,
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWVkNWJhMDhkNmQwMTYyMDcyYTYwNzg4NTRiOTQwNjE2M2Q4NTkyMzRiMGMyOTA5NWFjOWIyMDE1MGQzYWMzZmFiNzdkZDQ0MDMzMGQzZWQiLCJpYXQiOjE2NzU3NTA1MDAsIm5iZiI6MTY3NTc1MDUwMCwiZXhwIjoxNzA3Mjg2NTAwLCJzdWIiOiI5MzMiLCJzY29wZXMiOltdfQ.D_YETTNEt8ZehNHmU15bY5IAPy8QTC3ZV9YzhIrX3BZC2C6YV6W1QjYF5NfnIttEb7dqD-kpWn9llGnk7mIw29hmfdmfUN0yQeN2SPSMQgQdcoauqLfQAktU9nn5D6MyBVHgwA9iI5NvxoyrodWZ4zp6G_SEuGUzmVpSEdcPccKnlHtPHmsGhEcahngaIrF0tPfLrB0AuCXhmb1p9rJNnCkfoCvK-R81E_dFR5pzm6z0jMm0rEExd0kjkvtrVfls8laKxR17JHP9gx4Qgm1P-9gMtfHPt4VqTq57QHYjoxFkog3btw6Qq7QizwkDJnIuAJYw6kHz1UDsyYXXhmVLhctaBLirzJxbT7tdy0W-ByOfu9okXv9CTnIREAbFBbopdoL0L0jF7TXx_8l6V0RBuZEsoQ8d0ohPRE7dTU3clKApA50zEqTTehQTHG-Ghzn97pO8lY5d2ti5xO1GS1lopKuSYP1WdiLd5clQ51EPDbed9CMT4k8fqVyZHOonq_ITAexDMl_mHB3rpPFM4MfpWbx3jVsaUSbxLvK-hpufggIJlEsRgSD8yZIA8wUqfGzcbbtVbf1omiKa-1sopcjcW36q48gY-ZM3RHH8-KA98P0AgkjPTtlKGOMIpbDNCaduuc3F5qbID8cpzFPkEj0VGL45EsIIaYuZI5WjwTXFRVE",
                "Android",
                "1"
            )
        }
        else
        {
            Utils.InternetNotAvailableToast(requireContext())
        }

        //get api response from server
      //  viewModel.resultInnerOverView.observe(requireActivity()) {
//            when (it) {
//                is BaseResponse.Success ->
//                {
//                    binding.txIdName.text = it.data!!.data.name
//                    binding.txIdGender.text = it.data.data.gender
//                    binding.IdYearly.text = it.data.data.dobToAge()
//                    binding.dobId.text = it.data.data.getDob()
//                    binding.txidSubDetail.text = "#" + it.data.data.id.toString()
//                    binding.txidStartData.text = it.data.data.user_subscriptions.start_date
//                    binding.txidEndData.text = it.data.data.user_subscriptions.end_date
//                    if (it.data.data.user_subscriptions.status.equals(resources.getString(R.string.activeData), true))
//                    {
//                        binding.idActiveDetail.text = "Active"
//                        binding.idPurchaseNewSub.visibility = View.GONE
//                    }
//                    else
//                    {
//                        binding.idActiveDetail.setBackgroundResource(R.drawable.status_expired_tag)
//                        binding.idActiveDetail.text = resources.getString(R.string.Expiredstr)
//                        binding.idPurchaseNewSub.visibility = View.VISIBLE
//                    }
//                    const.cancel()
//                }
//
//                is BaseResponse.Loading -> {
//                    const.show()
//                }
//
//                is BaseResponse.Error -> {
//                    Toast.makeText(requireContext(), it.msg.toString(), Toast.LENGTH_SHORT).show()
//                    const.cancel()
//                }
//            }
//        }
        return binding.root
    }

    override fun setUserVisibleHint(isVisible: Boolean) {
        super.setUserVisibleHint(isVisible)
        if (isVisible) {
            val ftr: FragmentTransaction = requireFragmentManager().beginTransaction()
            ftr.detach(this).attach(this).commit()
        }
    }

}