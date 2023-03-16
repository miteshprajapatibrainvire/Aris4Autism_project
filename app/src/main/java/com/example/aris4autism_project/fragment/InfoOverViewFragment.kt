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


class InfoOverViewFragment() : Fragment() {

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
//        Glide.with(requireActivity())
//            .load(overViewData.getLearnerIcon.iconUrl)
//            .into(binding.imgIdDetailIcon)

        // val typeToken=object : TypeToken<OverViewData>(){}.type

        //initialize viewmodel
        viewModel=ViewModelProvider(requireActivity(),OverViewViewModelFactory(requireActivity())).get(OverViewViewModel::class.java)

        if(Utils.isOnline(requireContext())) {
            //call api
            viewModel.getOverViewInnerDetails(
                "e12d581cd2e940e4add79e0f51925fb7"
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