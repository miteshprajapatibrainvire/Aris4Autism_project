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
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.adapter.ProfileAdapter
import com.example.aris4autism_project.databinding.FragmentBasicDetailsBinding
import com.example.aris4autism_project.model.BundleModel
import com.example.aris4autism_project.model.editlearnermodel.SingleUserEditLearnerModel
import com.example.aris4autism_project.model.userprofilemodel.ProfileIconResponseModel
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.model.subscriptionmodel.subscriptionmodelresponse.SubScriptionResponseModel
import com.example.aris4autism_project.model.subscriptionmodel.subscriptionmodelresponse.SubscriptionData
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
    lateinit var subscriptionArray: ArrayList<SubscriptionData>
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
        val constDialog = Constant.getDialogCustom(requireContext())
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

            if (it.toString().equals(resources.getString(R.string.fillalldetails)))
            {
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


        viewModelLearner =
            ViewModelProvider(requireActivity(), LearnerViewModelFactory(requireContext())).get(
                LearnerViewModel::class.java
            )
        viewModel = ViewModelProvider(
            requireActivity(), ProfileDetailViewModelFactory( requireContext())
        ).get(
            ProfileDetailViewModel::class.java
        )

        val genderArray = ArrayList<String>()
        genderArray.add(resources.getString(R.string.male))
        genderArray.add(resources.getString(R.string.female))
        genderArray.add(resources.getString(R.string.other))

        val adpString =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, genderArray)
        binding.idGenEd.setAdapter(adpString)

        binding.idDobEd.setOnClickListener {
            clickDatePicker()
        }
        if (Utils.isOnline(requireContext())) {
            viewmodelSubscription.getSubUserDetails()
            if (bundleModelData.uuid != null) {
                viewModelLearner.getEditLearnerResponse(
                    bundleModelData.uuid
                )
            }
            viewModel.getUserProfileIconDetail()
        }
        else
        {
            Utils.InternetNotAvailableToast(requireContext())
        }

        viewmodelSubscription.resultSubscription.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseHandler.Loading -> {
                    constDialog.show()
                }
                is ResponseHandler.OnFailed -> {
                    constDialog.cancel()
                }
                is ResponseHandler.OnSuccessResponse<ResponseData<SubScriptionResponseModel>?> -> {
                    constDialog.cancel()
                    val dataList = ArrayList<String>()
                    subscriptionArray.addAll(state.response?.data?.original?.data!!)
                    var p = 0
                    subsriptionTitle = ArrayList()
                    for (i in state.response.data?.original?.data!!) {
                        dataList.add(i.title)
                        p++
                    }
                    val newArray = dataList.toSet()
                    val updateArray = ArrayList<String>()
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
            }
        }

        binding.spSelectSubscription.setOnItemClickListener(object :
            AdapterView.OnItemClickListener {
            @SuppressLint("SetTextI18n")
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

        viewModelLearner.resultEditLearner.observe(viewLifecycleOwner,{
            state->
            when(state)
            {
                is ResponseHandler.Loading->{
                }

                is ResponseHandler.OnFailed->{
                }

                is ResponseHandler.OnSuccessResponse<ResponseData<SingleUserEditLearnerModel>?>->{
                    Log.e("responseAddLearner=",state.response!!.data!!.toString())
                    binding.idfullNameEd.setText(state.response.data?.name)
                    binding.idDobEd.setText(state.response.data?. dateOfBirth)

                    for(i in genderArray.indices)
                    {
                        if(genderArray[i].lowercase(Locale.ROOT)
                                .equals(state.response.data!!.gender.lowercase(Locale.ROOT),true))
                        {
                            binding.idGenEd.setText(binding.idGenEd.getAdapter().getItem(i).toString(), false)
                            break
                        }
                    }

                    for(i in subscriptionArray.indices)
                    {
                        if(subscriptionArray[i].title.lowercase(Locale.ROOT)
                                .equals(state.response.data!!.userSubscriptions.title.lowercase(Locale.ROOT),true))
                        {
                            binding.spSelectSubscription.setText(binding.spSelectSubscription.getAdapter().getItem(i).toString(), false)
                            binding.idtxSubscriptionId.text = "#" + subscriptionArray[i].subscriptionOrderId.toString()
                            binding.idtxstartdate.text = subscriptionArray[i].startDate
                            binding.idtxenddate.text = subscriptionArray[i].endDate
                            break
                        }
                    }
                }
            }
        })

        viewModel.resultProfileIcon.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseHandler.Loading -> {

                }
                is ResponseHandler.OnFailed -> {

                }
                is ResponseHandler.OnSuccessResponse<ResponseData<ProfileIconResponseModel>?> -> {
                    Log.e(
                        "ResponseAddNewLearner=",
                        state.response!!.data!!.Profileoriginal.toString()
                    )
                    binding.recyAddnewlearnerIcon.layoutManager =
                        GridLayoutManager(requireActivity(), 4)
                    binding.recyAddnewlearnerIcon.adapter =
                        ProfileAdapter(
                            state.response.data!!.Profileoriginal.data,
                            { deleteItem -> getItemSeleted(deleteItem) })
                }
            }
        }

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
            { datePicker, y, m, d ->
                val monthData = m + 1
                val strData: String = d.toString() + "/" + monthData.toString() + "/" + y.toString()
                dobSelect = strData
                val parser = SimpleDateFormat("dd/MM/yyyy")
                val formatter = SimpleDateFormat("yyyy-MM-dd")
                val changeDobFormat = parser.parse(strData)?.let { formatter.format(it) }
                binding.idDobEd.setText(changeDobFormat)
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