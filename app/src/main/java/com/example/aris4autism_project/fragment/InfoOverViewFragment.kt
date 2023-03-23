package com.example.aris4autism_project.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.databinding.FragmentInfoOverViewBinding
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.viewmodel.OverViewViewModel
import com.example.aris4autism_project.viewmodel.OverViewViewModelFactory
import com.example.food_nutrition_recipe_app.model.clonemodel.OverViewInnerDetailResponse
import com.google.gson.reflect.TypeToken


class InfoOverViewFragment() : Fragment() {

    lateinit var binding: FragmentInfoOverViewBinding
    lateinit var viewModel: OverViewViewModel

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoOverViewBinding.inflate(inflater)

        //call dialog box
        val constDialog = Constant.getDialogCustom(requireContext())



        // val typeToken=object : TypeToken<OverViewData>(){}.type

        //initialize viewmodel
        viewModel =
            ViewModelProvider(requireActivity(), OverViewViewModelFactory(requireActivity())).get(
                OverViewViewModel::class.java
            )

        if (Utils.isOnline(requireContext())) {
            //call api
            viewModel.getOverViewInnerDetails(
                Constant.editUserId
            )
        } else {
            Utils.InternetNotAvailableToast(requireContext())
        }

        //get api response from server
        viewModel.resultInnerOverView.observe(viewLifecycleOwner, { state ->
            when (state) {
                is ResponseHandler.Loading -> {
                    constDialog.show()
                }
                is ResponseHandler.OnFailed -> {
                    constDialog.cancel()
                }
                is ResponseHandler.OnSuccessResponse<ResponseData<OverViewInnerDetailResponse>?> -> {
                    Log.e("infooverviewDetails=", state.response?.data.toString())
                    constDialog.cancel()
                    binding.txIdName.text = state.response?.data!!.name
                    binding.txIdGender.text = state.response?.data!!.gender
                    binding.IdYearly.text = state.response?.data!!.dobToAge()
                    binding.dobId.text = "DOB :"+state.response?.data!!.date_of_birth

                    //glide library for fetch image
        Glide.with(requireActivity())
            .load(state.response?.data!!.get_learner_icon.icon_url)
            .into(binding.imgIdDetailIcon)

                    binding.txidSubDetail.text = "#" + state.response?.data!!.id.toString()
                    if (state.response?.data!!.user_subscriptions != null) {
                        binding.txidStartData.text =
                            state.response?.data!!.user_subscriptions.start_date
                        binding.txidEndData.text =
                            state.response?.data!!.user_subscriptions.end_date
                        if (state.response?.data!!.user_subscriptions.status.equals(
                                resources.getString(
                                    R.string.activeData
                                ), true
                            )
                        ) {
                            binding.idActiveDetail.text = "Active"
                            binding.idActiveDetail.setBackgroundResource(R.drawable.status_tag_bg)
                            binding.idPurchaseNewSub.visibility = View.GONE
                        } else {
                            binding.idActiveDetail.setBackgroundResource(R.drawable.status_expired_tag)
                            binding.idActiveDetail.text = resources.getString(R.string.Expiredstr)
                            binding.idPurchaseNewSub.visibility = View.VISIBLE
                        }
                    }
                    else
                    {
                        binding.idActiveDetail.setBackgroundResource(R.drawable.status_expired_tag)
                        binding.idActiveDetail.text = resources.getString(R.string.Expiredstr)
                        binding.idPurchaseNewSub.visibility = View.VISIBLE
                    }
                }
            }
        })


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