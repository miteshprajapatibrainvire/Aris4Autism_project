package com.example.aris4autism_project.fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
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
import com.example.aris4autism_project.model.SummaryPassModel
import com.example.aris4autism_project.model.editlearnermodel.SingleEditUserSubscriptions
import com.example.aris4autism_project.model.editlearnermodel.SingleUserEditLearnerModel
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.model.subscriptionmodel.subscriptionmodelresponse.SubScriptionResponseModel
import com.example.aris4autism_project.model.subscriptionmodel.subscriptionmodelresponse.SubscriptionData
import com.example.aris4autism_project.model.userprofilemodel.ProfileIconResponseModel
import com.example.aris4autism_project.viewmodel.*
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*


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
    var imgIdSelected: Int = 0
    var imgIconUrlSelected: String = ""
    var uuid: String = ""
    var subscriptionId: String = ""
    var userSubscription: SingleEditUserSubscriptions? = null
    val updateArray = ArrayList<String>()


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBasicDetailsBinding.inflate(inflater)
        val constDialog = Constant.getDialogCustom(requireContext())
        subscriptionArray = ArrayList()
        subsriptionTitle = ArrayList()

        viewModelLearner =
            ViewModelProvider(requireActivity(), LearnerViewModelFactory(requireContext())).get(
                LearnerViewModel::class.java
            )
        binding.idfullNameEd.addTextChangedListener(textWatcherFullName)
        binding.idGenEd.addTextChangedListener(textWatcherGender)
        binding.idDobEd.addTextChangedListener(textWatcherDateOfBirth)
        binding.spSelectSubscription.addTextChangedListener(txWathcerSubscription)

        binding.idGenEd.inputType = InputType.TYPE_NULL
        binding.spSelectSubscription.inputType = InputType.TYPE_NULL

        viewmodelSubscription = ViewModelProvider(
            requireActivity(),
            SubScriptionViewModelFactory(requireContext())
        ).get(SubScriptionViewModel::class.java)

        viewmodelBasicValid = ViewModelProvider(
            requireActivity(),
            BasicDetailValidationViewModelFactory(requireActivity())
        ).get(BasicDetailValidation::class.java)

        viewModel = ViewModelProvider(
            requireActivity(), ProfileDetailViewModelFactory(requireContext())
        ).get(
            ProfileDetailViewModel::class.java
        )

        binding.basicValidatemodel = viewmodelBasicValid
        binding.lifecycleOwner = this

        viewmodelBasicValid.resultBasicDetailValidation = MutableLiveData<String>()
        viewModelLearner.resultEditLearner =
            MutableLiveData<ResponseHandler<ResponseData<SingleUserEditLearnerModel>?>>()

        val genderArray = ArrayList<String>()
        genderArray.add(resources.getString(R.string.male))
        genderArray.add(resources.getString(R.string.female))
        genderArray.add(resources.getString(R.string.other))


        if (Utils.isOnline(requireContext())) {

            if (bundleModelData.uuid.isNotEmpty()) {

                bundleModelData.imgId?.let {
                    if (it == "null") {
                    } else {
                        imgIdSelected = it.toInt()
                    }
                }
                imgIconUrlSelected = bundleModelData.iconImg
                uuid = bundleModelData.uuid
                subscriptionId = bundleModelData.subscriptionId
                Log.e("imgId=", bundleModelData.imgId.toString())
                Log.e("imgIcon=", bundleModelData.iconImg)
                // }
                viewModelLearner.getEditLearnerResponse(bundleModelData.uuid)
            } else {
                val adpString = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, genderArray)
                binding.idGenEd.setAdapter(adpString)
            }
            viewModel.getUserProfileIconDetail()
        } else {
            Utils.InternetNotAvailableToast(requireContext())
        }

        binding.idbottomRecyclerView.visibility = View.GONE



        binding.idDobEd.setOnClickListener {
            clickDatePicker()
        }

        //validation code
        viewmodelBasicValid.getBasicDetailResult().observe(viewLifecycleOwner) {

            if (it.toString().equals(resources.getString(R.string.iconStatus))) {
                if (!booleanState) {
                    binding.idbottomRecyclerView.visibility = View.VISIBLE
                }
            }

            if (it.toString().equals(resources.getString(R.string.fillalldetails))) {

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
                    if (booleanState) {
                        binding.idbottomRecyclerView.visibility = View.GONE
                        val summaryModelframe = SummaryPassModel(
                            uuid,
                            imgIdSelected,
                            imgIconUrlSelected,
                            binding.idfullNameEd.text.toString(),
                            subscriptionId,
                            binding.idGenEd.text.toString(),
                            binding.idDobEd.text.toString(),
                            binding.idtxstartdate.text.toString(),
                            binding.idtxenddate.text.toString(),
                            monthlyPlan
                        )
                        viewmodelBasicValid.learnerModelResponse.postValue(summaryModelframe)
                        val viewpager = activity?.findViewById<ViewPager2>(R.id.viewpagerID)
                        viewpager?.currentItem = 1
                        // Constant.editUserId = bundleModelData.uuid
                        checkObserverModelClick = false
                    } else {
                        binding.idbottomRecyclerView.visibility = View.VISIBLE
                    }
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

                    Log.e("subscriptiondatalist=", state.response?.data?.original?.data.toString())
                    Log.e("Subscriptionid=", state.response?.data!!.original.data.toString())
                    Log.e(
                        "Learnerid=",
                        state.response?.data!!.original.data[0].learnerId.toString()
                    )

                    state.response?.data!!.original.data.let {

                        subscriptionArray.addAll(it)
                    }
                    var p = 0
                    subsriptionTitle = ArrayList()
                    for (i in state.response.data?.original?.data!!) {
                        dataList.add(i.title)
                        p++
                    }
                    val newArray = dataList.toSet()
                    updateArray.clear()
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

                    for (i in subscriptionArray.indices) {
                        if (subscriptionArray[i].title.lowercase(Locale.ROOT)
                                .equals(
                                    userSubscription?.title?.lowercase(
                                        Locale.ROOT
                                    ), true
                                )
                        ) {
                            binding.spSelectSubscription.setText(
                                binding.spSelectSubscription.getAdapter().getItem(i).toString(),
                                false
                            )
                            binding.idtxSubscriptionId.text =
                                "#" + subscriptionArray[i].subscriptionOrderId.toString()
                            binding.idtxstartdate.text = subscriptionArray[i].startDate
                            binding.idtxenddate.text = subscriptionArray[i].endDate
                            monthlyPlan = subscriptionArray[i].title
                            break
                        }
                    }
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
                        if (subscriptionId.isEmpty()) {
                            subscriptionId = i.assignedTo.subscriptionId.toString()
                            Log.e("subscriptionIdSpinner=", subscriptionId.toString())
                        }
                    }
                }
                Toast.makeText(requireContext(), item.toString(), Toast.LENGTH_SHORT).show()
            }
        })


        viewModelLearner.resultEditLearner.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseHandler.Loading -> {
                }

                is ResponseHandler.OnFailed -> {
                }

                is ResponseHandler.OnSuccessResponse<ResponseData<SingleUserEditLearnerModel>?> -> {
                    Log.e("responseAddLearner=", state.response!!.data!!.toString())
                    // updateArray.clear()
                    subscriptionArray.clear()
                    binding.spSelectSubscription.setText("")

                    binding.idfullNameEd.setText(state.response.data?.name)
                    binding.idDobEd.setText(state.response.data?.dateOfBirth)
                    if (state.response.data?.userSubscriptions != null) {
                        binding.spSelectSubscription.setText("")
                        binding.idtxSubscriptionId.text = ""
                        binding.idtxstartdate.text = ""
                        binding.idtxenddate.text = ""
                        viewmodelSubscription.getSubUserDetails()
                    } else {

                    }

                    userSubscription = state.response.data!!.userSubscriptions
                    val adpString =
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_list_item_1,
                            genderArray
                        )
                    binding.idGenEd.setAdapter(adpString)
                    binding.idGenEd.setText(state.response.data!!.gender, false)

                    // binding.idGenEd.setSelection(binding.idGenEd.getText().length)

//                    binding.idGenEd.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            autoCompleteTextView.setText("PREM");
//                            autoCompleteTextView.showDropDown();
//                        }
//                    }, 10);

//                            for (i in genderArray.indices) {
//                        state.response.data.let {
//                            if (genderArray[i].lowercase(Locale.ROOT)
//                                    .equals(it?.gender?.lowercase(Locale.ROOT), true)
//                            ) {
//                                positionGet=i
//                            }
//                        }
//                    }

//                    binding.idGenEd.postDelayed(Runnable {
//                       // binding.idGenEd.setText(state.response.data!!.gender)
//                        binding.idGenEd.setText(
//                    binding.idGenEd.setText(
//                                    binding.idGenEd.getAdapter().getItem(positionGet).toString(), false
//                    )
                    //)
//                        binding.idGenEd.showDropDown()
//                    },10)

                    // binding.idGenEd.setText(state.response.data!!.gender)

//                    for (i in genderArray.indices) {
//                        state.response.data.let {
//                            if (genderArray[i].lowercase(Locale.ROOT)
//                                    .equals(it?.gender?.lowercase(Locale.ROOT), true)
//                            ) {
//                                binding.idGenEd.setText(
//                                    binding.idGenEd.getAdapter().getItem(i).toString(), false
//                                )
//                            }
//                        }
//                    }
                    //   genderArray.clear()
                }
            }
        }

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

                    binding.idfullNameEd.setText("")
                    binding.idGenEd.setText("")
                    binding.idDobEd.setText("")
                    binding.spSelectSubscription.setText("")

                    binding.recyAddnewlearnerIcon.layoutManager =
                        GridLayoutManager(requireActivity(), 4)
                    ProfileAdapter(
                        state.response.data!!.Profileoriginal.data,
                        { deleteItem -> getItemSeleted(deleteItem) },
                        { imgId, icon -> getImgSelected(imgId, icon) }).also {
                        binding.recyAddnewlearnerIcon.adapter = it
                    }
                }
            }
        }

        return binding.root
    }

    private fun getImgSelected(imgId: Int, icon: String) {
        imgIdSelected = imgId
        imgIconUrlSelected = icon
        Log.e("imgIdSelected=", imgIdSelected.toString() + "iconSelected=" + imgIconUrlSelected)
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