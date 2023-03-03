package com.example.aris4autism_project.fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.aris4autism_project.BaseResponse
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.adapter.ProfileAdapter
import com.example.aris4autism_project.databinding.FragmentBasicDetailsBinding
import com.example.aris4autism_project.model.BundleModel
import com.example.aris4autism_project.model.DataXXXXXXXXXXXXX
import com.example.aris4autism_project.viewmodel.*
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BasicDetailsFragment(val bundleModelData: BundleModel) : Fragment() {

    lateinit var binding: FragmentBasicDetailsBinding
    lateinit var viewModel: ProfileDetailViewModel
    lateinit var viewModelLearner: LearnerViewModel
    lateinit var viewmodelSubscription: SubScriptionViewModel
    lateinit var subscriptionArray: ArrayList<DataXXXXXXXXXXXXX>
    lateinit var subsriptionTitle: ArrayList<String>
    lateinit var viewmodelBasicValid: BasicDetailValidation
    var checkObserverModelClick: Boolean = false
    var booleanState: Boolean = false
    var monthlyPlan: String = " "
    var dobSelect: String = " "

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBasicDetailsBinding.inflate(inflater)

        subscriptionArray = ArrayList()
        subsriptionTitle = ArrayList()

        viewmodelSubscription = ViewModelProvider(
            requireActivity(),
            SubScriptionViewModelFactory(requireContext())
        ).get(SubScriptionViewModel::class.java)

        binding.idbottomRecyclerView.visibility = View.GONE

        viewmodelBasicValid = ViewModelProvider(
            requireActivity(),
            BasicDetailValidationViewModelFactory(requireActivity())
        ).get(BasicDetailValidation::class.java)
        binding.basicValidatemodel = viewmodelBasicValid
        binding.lifecycleOwner = this

        binding.idfullNameEd.addTextChangedListener(textWatcherFullName)
        binding.idGenEd.addTextChangedListener(textWatcherGender)
        binding.idDobEd.addTextChangedListener(textWatcherDateOfBirth)
        binding.spSelectSubscription.addTextChangedListener(txWathcerSubscription)
        viewmodelBasicValid.resultBasicDetailValidation= MutableLiveData()

        viewmodelBasicValid.getBasicDetailResult().observe(viewLifecycleOwner) {

            if (it.toString().equals(resources.getString(R.string.iconStatus))) {
                if (!booleanState) {
                    binding.idbottomRecyclerView.visibility = View.VISIBLE
                }
            }

            if (it.toString().equals(resources.getString(R.string.fillalldetails).toString())) {
                Log.e("basicResultValidate=", it.toString())
                binding.idtxFullname.error = resources.getString(R.string.basicDetailName)
                binding.idtxFullname.isEnabled = true
                setBorderColor(binding.idtxFullname)

                if (!booleanState) {
                    binding.idbottomRecyclerView.visibility = View.VISIBLE
                }

                binding.idtxGen.error = resources.getString(R.string.basicDetailGen)
                binding.idtxGen.isEnabled = true
                setBorderColor(binding.idtxGen)

                binding.idtxDobdata.error = resources.getString(R.string.basicdateofbirth)
                binding.idtxDobdata.isEnabled = true
                setBorderColor(binding.idtxDobdata)

                binding.txlayoutBasicSubscriptionSelect.error =
                    resources.getString(R.string.basicselectsub)
                binding.txlayoutBasicSubscriptionSelect.isEnabled = true
                setBorderColor(binding.txlayoutBasicSubscriptionSelect)

            } else {

                if (it.toString().equals(resources.getString(R.string.validcredential))) {


                        val viewpager = activity?.findViewById<ViewPager2>(R.id.viewpagerID)
                        viewpager?.currentItem = 1
                        Constant.editUserId = bundleModelData.uuid

                        SummaryFragment().setSummaryLayoutData(
                            binding.idfullNameEd.text.toString(),
                            binding.idGenEd.text.toString(),
                            binding.idDobEd.text.toString(),
                            binding.idtxSubscriptionId.text.toString(),
                            binding.idtxstartdate.text.toString(),
                            binding.idtxenddate.text.toString(),
                            monthlyPlan
                        )
                        BasicDetailsFragment(bundleModelData).onDetach()
                        checkObserverModelClick = false
                    }



                if (it.toString().equals(resources.getString(R.string.basicDetailName))) {
                    binding.idtxFullname.error = resources.getString(R.string.basicDetailName)
                    binding.idtxFullname.isEnabled = true
                    setBorderColor(binding.idtxFullname)
                }
                if (it.toString().equals(resources.getString(R.string.basicDetailGen))) {
                    binding.idtxGen.error = resources.getString(R.string.basicDetailGen)
                    binding.idtxGen.isEnabled = true
                    setBorderColor(binding.idtxGen)
                }
                if (it.toString().equals(resources.getString(R.string.basicdateofbirth))) {
                    binding.idtxDobdata.error = resources.getString(R.string.basicdateofbirth)
                    binding.idtxDobdata.isEnabled = true
                    setBorderColor(binding.idtxDobdata)
                }
                if (it.toString().equals(resources.getString(R.string.basicselectsub))) {
                    binding.txlayoutBasicSubscriptionSelect.error =
                        resources.getString(R.string.basicselectsub)
                    binding.txlayoutBasicSubscriptionSelect.isEnabled = true
                    setBorderColor(binding.txlayoutBasicSubscriptionSelect)
                }
            }
        }

        val constDialog = Constant.getDialogCustom(requireContext())
        viewModelLearner =
            ViewModelProvider(requireActivity(), LearnerViewModelFactory(requireContext())).get(
                LearnerViewModel::class.java
            )
        viewModel = ViewModelProvider(
            requireActivity(), ProfileDetailViewModelFactory(requireContext())
        ).get(
            ProfileDetailViewModel::class.java
        )

        var genderArray = ArrayList<String>()
        genderArray.add("Male")
        genderArray.add("Female")
        genderArray.add("Other")

        var adpString =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, genderArray)
        binding.idGenEd.setAdapter(adpString)

        binding.idDobEd.setOnClickListener {
            clickDatePicker()
        }
        Log.e("=uuid=", bundleModelData.uuid)
        if (Utils.isOnline(requireContext())) {

            if (bundleModelData.uuid != null) {
                viewmodelSubscription.getSubUserDetails(
                    "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWVkNWJhMDhkNmQwMTYyMDcyYTYwNzg4NTRiOTQwNjE2M2Q4NTkyMzRiMGMyOTA5NWFjOWIyMDE1MGQzYWMzZmFiNzdkZDQ0MDMzMGQzZWQiLCJpYXQiOjE2NzU3NTA1MDAsIm5iZiI6MTY3NTc1MDUwMCwiZXhwIjoxNzA3Mjg2NTAwLCJzdWIiOiI5MzMiLCJzY29wZXMiOltdfQ.D_YETTNEt8ZehNHmU15bY5IAPy8QTC3ZV9YzhIrX3BZC2C6YV6W1QjYF5NfnIttEb7dqD-kpWn9llGnk7mIw29hmfdmfUN0yQeN2SPSMQgQdcoauqLfQAktU9nn5D6MyBVHgwA9iI5NvxoyrodWZ4zp6G_SEuGUzmVpSEdcPccKnlHtPHmsGhEcahngaIrF0tPfLrB0AuCXhmb1p9rJNnCkfoCvK-R81E_dFR5pzm6z0jMm0rEExd0kjkvtrVfls8laKxR17JHP9gx4Qgm1P-9gMtfHPt4VqTq57QHYjoxFkog3btw6Qq7QizwkDJnIuAJYw6kHz1UDsyYXXhmVLhctaBLirzJxbT7tdy0W-ByOfu9okXv9CTnIREAbFBbopdoL0L0jF7TXx_8l6V0RBuZEsoQ8d0ohPRE7dTU3clKApA50zEqTTehQTHG-Ghzn97pO8lY5d2ti5xO1GS1lopKuSYP1WdiLd5clQ51EPDbed9CMT4k8fqVyZHOonq_ITAexDMl_mHB3rpPFM4MfpWbx3jVsaUSbxLvK-hpufggIJlEsRgSD8yZIA8wUqfGzcbbtVbf1omiKa-1sopcjcW36q48gY-ZM3RHH8-KA98P0AgkjPTtlKGOMIpbDNCaduuc3F5qbID8cpzFPkEj0VGL45EsIIaYuZI5WjwTXFRVE",
                    "Android",
                    "1"
                )

                viewModelLearner.getEditLearnerResponse(
                    bundleModelData.uuid,
                    "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWVkNWJhMDhkNmQwMTYyMDcyYTYwNzg4NTRiOTQwNjE2M2Q4NTkyMzRiMGMyOTA5NWFjOWIyMDE1MGQzYWMzZmFiNzdkZDQ0MDMzMGQzZWQiLCJpYXQiOjE2NzU3NTA1MDAsIm5iZiI6MTY3NTc1MDUwMCwiZXhwIjoxNzA3Mjg2NTAwLCJzdWIiOiI5MzMiLCJzY29wZXMiOltdfQ.D_YETTNEt8ZehNHmU15bY5IAPy8QTC3ZV9YzhIrX3BZC2C6YV6W1QjYF5NfnIttEb7dqD-kpWn9llGnk7mIw29hmfdmfUN0yQeN2SPSMQgQdcoauqLfQAktU9nn5D6MyBVHgwA9iI5NvxoyrodWZ4zp6G_SEuGUzmVpSEdcPccKnlHtPHmsGhEcahngaIrF0tPfLrB0AuCXhmb1p9rJNnCkfoCvK-R81E_dFR5pzm6z0jMm0rEExd0kjkvtrVfls8laKxR17JHP9gx4Qgm1P-9gMtfHPt4VqTq57QHYjoxFkog3btw6Qq7QizwkDJnIuAJYw6kHz1UDsyYXXhmVLhctaBLirzJxbT7tdy0W-ByOfu9okXv9CTnIREAbFBbopdoL0L0jF7TXx_8l6V0RBuZEsoQ8d0ohPRE7dTU3clKApA50zEqTTehQTHG-Ghzn97pO8lY5d2ti5xO1GS1lopKuSYP1WdiLd5clQ51EPDbed9CMT4k8fqVyZHOonq_ITAexDMl_mHB3rpPFM4MfpWbx3jVsaUSbxLvK-hpufggIJlEsRgSD8yZIA8wUqfGzcbbtVbf1omiKa-1sopcjcW36q48gY-ZM3RHH8-KA98P0AgkjPTtlKGOMIpbDNCaduuc3F5qbID8cpzFPkEj0VGL45EsIIaYuZI5WjwTXFRVE",
                    "Android",
                    "1"
                )

            }

            viewModel.getUserProfileIconDetail(
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiOWVkNWJhMDhkNmQwMTYyMDcyYTYwNzg4NTRiOTQwNjE2M2Q4NTkyMzRiMGMyOTA5NWFjOWIyMDE1MGQzYWMzZmFiNzdkZDQ0MDMzMGQzZWQiLCJpYXQiOjE2NzU3NTA1MDAsIm5iZiI6MTY3NTc1MDUwMCwiZXhwIjoxNzA3Mjg2NTAwLCJzdWIiOiI5MzMiLCJzY29wZXMiOltdfQ.D_YETTNEt8ZehNHmU15bY5IAPy8QTC3ZV9YzhIrX3BZC2C6YV6W1QjYF5NfnIttEb7dqD-kpWn9llGnk7mIw29hmfdmfUN0yQeN2SPSMQgQdcoauqLfQAktU9nn5D6MyBVHgwA9iI5NvxoyrodWZ4zp6G_SEuGUzmVpSEdcPccKnlHtPHmsGhEcahngaIrF0tPfLrB0AuCXhmb1p9rJNnCkfoCvK-R81E_dFR5pzm6z0jMm0rEExd0kjkvtrVfls8laKxR17JHP9gx4Qgm1P-9gMtfHPt4VqTq57QHYjoxFkog3btw6Qq7QizwkDJnIuAJYw6kHz1UDsyYXXhmVLhctaBLirzJxbT7tdy0W-ByOfu9okXv9CTnIREAbFBbopdoL0L0jF7TXx_8l6V0RBuZEsoQ8d0ohPRE7dTU3clKApA50zEqTTehQTHG-Ghzn97pO8lY5d2ti5xO1GS1lopKuSYP1WdiLd5clQ51EPDbed9CMT4k8fqVyZHOonq_ITAexDMl_mHB3rpPFM4MfpWbx3jVsaUSbxLvK-hpufggIJlEsRgSD8yZIA8wUqfGzcbbtVbf1omiKa-1sopcjcW36q48gY-ZM3RHH8-KA98P0AgkjPTtlKGOMIpbDNCaduuc3F5qbID8cpzFPkEj0VGL45EsIIaYuZI5WjwTXFRVE",
                "Android",
                "1"
            )
        } else {
            Utils.InternetNotAvailableToast(requireContext())
        }


        viewmodelSubscription.resultSubscription.observe(requireActivity(), {
            when (it) {
                is BaseResponse.Success -> {
                    Log.e("basicSubscription=", it.data!!.data.original.data.toString())
                    subscriptionArray.addAll(it.data!!.data.original.data)
                    var dataList = ArrayList<String>()
                    var p = 0
                    subsriptionTitle = ArrayList()
                    for (i in it.data.data.original.data) {
                        dataList.add(i.title)
                        p++
                    }
                    var newArray = dataList.toSet()
                    var updateArray = ArrayList<String>()
                    for (i in newArray) {
                        updateArray.add(i)
                        subsriptionTitle.add(i)
                    }

                    binding.spSelectSubscription.setAdapter(
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_list_item_1,
                            updateArray
                        )
                    )

                }
                is BaseResponse.Loading -> {

                }
                is BaseResponse.Error -> {

                }
            }
        })


        binding.spSelectSubscription.setOnItemClickListener(object :
            AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val item = parent?.getItemAtPosition(position)
                for (i in subscriptionArray) {
                    if (item!!.equals(i.title)) {
                        monthlyPlan = i.title
                        binding.idtxSubscriptionId.text = "#" + i.subscriptionOrderId.toString()
                        binding.idtxstartdate.text = i.startDate
                        binding.idtxenddate.text = i.endDate
                    }
                }
                Toast.makeText(requireContext(), item.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        viewModelLearner.resultEditLearner.observe(
            requireActivity()
        ) {
            when (it) {
                is BaseResponse.Success -> {
                    Log.e("EditLearner=", it.data!!.data.toString())
                    binding.idfullNameEd.setText(it.data.data.name)
                    binding.idGenEd.setText(it.data.data.gender)
                    binding.idDobEd.setText(it.data.data.dateOfBirth)
//                    binding.spSelectSubscription.setText(it.data.data.userSubscriptions.title)
                    binding.idtxSubscriptionId.setText("#" + it.data.data.subscriptionId.toString())
                    binding.idtxstartdate.setText(it.data.data.userSubscriptions.startDate)
                    binding.idtxenddate.setText(it.data.data.userSubscriptions.endDate)
                    constDialog.cancel()
                }
                is BaseResponse.Error -> {
                    Toast.makeText(requireContext(), it.msg.toString(), Toast.LENGTH_SHORT).show()
                    constDialog.cancel()
                }
                is BaseResponse.Loading -> {
                    constDialog.show()
                }
            }
        }


        viewModel.resultProfileIcon.observe(requireActivity(), {

            when (it) {
                is BaseResponse.Success -> {
                    Log.e("ResponseAddNewLearner=", it.data!!.data.original.toString())
                    binding.recyAddnewlearnerIcon.layoutManager =
                        GridLayoutManager(requireActivity(), 4)
                    binding.recyAddnewlearnerIcon.adapter =
                        ProfileAdapter(it.data.data.original.data,
                            { deleteItem -> getItemSeleted(deleteItem) })
                }

                is BaseResponse.Error -> {

                }

                is BaseResponse.Loading -> {

                }
            }
        })

//        binding.idbtnaddnewNext.setOnClickListener {

//            val viewpager = activity?.findViewById<ViewPager2>(R.id.viewpagerID)
//            viewpager?.currentItem = 1
//            Constant.editUserId = bundleModelData.uuid
//
//            SummaryFragment().setSummaryLayoutData(
//                binding.idfullNameEd.text.toString(),
//                binding.idGenEd.text.toString(),
//                binding.idDobEd.text.toString(),
//                binding.idtxSubscriptionId.text.toString(),
//                binding.idtxstartdate.text.toString(),
//                binding.idtxenddate.text.toString(),
//                monthlyPlan
//            )
//        }

        return binding.root
    }

    private fun getItemSeleted(deleteItem: Boolean) {
        if (deleteItem) {
            booleanState = deleteItem
            binding.idbottomRecyclerView.visibility = View.GONE
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun clickDatePicker() {
        val myCalander = Calendar.getInstance()
        val year = myCalander.get(Calendar.YEAR)
        val month = myCalander.get(Calendar.MONTH)
        val day = myCalander.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { datePicker, y, m, d ->
                val monthData = m + 1
                val strData: String = d.toString() + "/" + monthData.toString() + "/" + y.toString()
                dobSelect = strData
                val parser = SimpleDateFormat("yyyy-MM-dd")
                val formatter = SimpleDateFormat("dd/MM/yyyy")

                //Log.e("calculateAge=",dobToAge(binding.dobEd.text.toString()))

                binding.idDobEd.setText(strData)

            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 8640000
        dpd.show()
    }

    private val textWatcherFullName = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            binding.idtxFullname.isErrorEnabled = false
            binding.idtxFullname.boxStrokeErrorColor =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray))
            binding.idtxFullname.boxStrokeWidth = 1
            binding.idtxFullname.boxStrokeWidthFocused = 1
            binding.idtxFullname.boxStrokeColor = Color.parseColor("#1E4884")

        }
    }
    private val textWatcherDateOfBirth = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            binding.idtxDobdata.isErrorEnabled = false
            binding.idtxDobdata.boxStrokeErrorColor =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray))
            binding.idtxDobdata.boxStrokeColor = 1
            binding.idtxDobdata.boxStrokeWidthFocused = 1
            binding.idtxDobdata.boxStrokeColor = Color.parseColor("#1E4884")
        }
    }

    private val txWathcerSubscription = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            binding.txlayoutBasicSubscriptionSelect.isErrorEnabled = false
            binding.txlayoutBasicSubscriptionSelect.boxStrokeErrorColor =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray))
            binding.txlayoutBasicSubscriptionSelect.boxStrokeColor = 1
            binding.txlayoutBasicSubscriptionSelect.boxStrokeWidthFocused = 1
            binding.txlayoutBasicSubscriptionSelect.boxStrokeColor = Color.parseColor("#1E4884")
        }

    }

    private val textWatcherGender = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            binding.idtxGen.isErrorEnabled = false
            binding.idtxGen.boxStrokeErrorColor =
                ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.gray))
            binding.idtxGen.boxStrokeColor = 1
            binding.idtxGen.boxStrokeWidthFocused = 1
            binding.idtxGen.boxStrokeColor = Color.parseColor("#1E4884")
        }

    }


    private fun setBorderColor(txLayoutdata: TextInputLayout) {
        txLayoutdata.boxStrokeErrorColor =
            ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.red))
        txLayoutdata.boxStrokeWidth = 2
        txLayoutdata.boxStrokeWidthFocused = 2
        txLayoutdata.boxStrokeColor = Color.RED
    }


}