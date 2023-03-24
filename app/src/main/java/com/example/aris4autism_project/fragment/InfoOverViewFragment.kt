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
                    state.response?.data.let{
                        binding.name = it?.name
                        binding.gender = it?.gender
                        binding.dobtoage = it?.dobToAge()
                        binding.dob= "DOB :"+it?.date_of_birth
                        Glide.with(requireActivity())
                            .load(it?.get_learner_icon?.icon_url)
                            .into(binding.imgIdDetailIcon)
                        binding.subscriptionId = "#" + it?.id.toString()

                        if (it?.user_subscriptions != null)
                        {
                            binding.startDate =
                                it.user_subscriptions.start_date
                            binding.endDate =
                                it.user_subscriptions.end_date
                            binding.monthlyplan=it.user_subscriptions.title

                            if (it.user_subscriptions.status.equals(
                                    resources.getString(
                                        R.string.activeData
                                    ), true
                                )
                            ) {
                                binding.active = "Active"
                                binding.activeStatus=true
                                binding.idPurchaseNewSub.visibility = View.GONE
                            } else {
                                binding.active="Expired"
                                binding.activeStatus=false
                                binding.idPurchaseNewSub.visibility = View.VISIBLE
                            }
                        }
                        else
                        {
                            binding.active="Expired"
                            binding.activeStatus=false
                            binding.idPurchaseNewSub.visibility = View.VISIBLE
                        }
                    }

                    //glide library for fetch image




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