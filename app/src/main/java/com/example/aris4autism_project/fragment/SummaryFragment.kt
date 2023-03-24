package com.example.aris4autism_project.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.adapter.DiagnosAdapter
import com.example.aris4autism_project.databinding.FragmentSummaryBinding
import com.example.aris4autism_project.model.SummaryPassModel
import com.example.aris4autism_project.model.diagnosismodel.DiagnosisDetailResponseModel
import com.example.aris4autism_project.model.learnermodel.AddNewLearnerResponse
import com.example.aris4autism_project.model.learnermodel.CreateNewLearnerModel
import com.example.aris4autism_project.model.learnermodel.LearnerDiagnosisData
import com.example.aris4autism_project.model.learnermodel.LearnerEditModelResponse
import com.example.aris4autism_project.model.networkresponse.ResponseData
import com.example.aris4autism_project.model.networkresponse.ResponseHandler
import com.example.aris4autism_project.viewmodel.*
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import java.util.*
import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
class SummaryFragment : Fragment() {

    lateinit var binding: FragmentSummaryBinding

    lateinit var addviewModel: LearnerViewModel
    var diagnosisDataLearner = ArrayList<LearnerDiagnosisData>()
    private var diagnosisId = ArrayList<String>()
    var summaryModelData: SummaryPassModel? = null
    lateinit var viewModelBasiclearner: BasicDetailValidation
    var imgId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSummaryBinding.inflate(inflater, container, false)

        viewModelBasiclearner = ViewModelProvider(
            requireActivity(), BasicDetailValidationViewModelFactory(requireContext())
        ).get(BasicDetailValidation::class.java)

        //get loading dialogbox
        val constDialog = Constant.getDialogCustom(requireContext())

        binding.lifecycleOwner = this

        viewModelBasiclearner.learnerModelResponse.observe(viewLifecycleOwner) {
            binding.summaryModel = it
            summaryModelData = it
            imgId = it.idData
            Glide.with(requireContext()).load(it.iconUrl).into(binding.imgIconId)

        }

        viewModelBasiclearner.diagnosisArray.observe(viewLifecycleOwner) {
            val diagnosisSummaryArray = ArrayList<LearnerDiagnosisData>()
            for (i in it) {
                if (i.isItemChecked) {
                    diagnosisId.add(i.id.toString())
                    diagnosisSummaryArray.add(
                        LearnerDiagnosisData(
                            i.id, 0, 0, i.title, "slug"
                        )
                    )
                }
            }
            binding.recySummary.adapter = DiagnosAdapter(diagnosisSummaryArray)
        }


        if (arguments?.getParcelableArrayList<DiagnosisDetailResponseModel>("dianosisArray") != null) {
            val diagnosisArray =
                arguments?.getParcelableArrayList<DiagnosisDetailResponseModel>("dianosisArray") as ArrayList<DiagnosisDetailResponseModel>

            if (arguments?.getSerializable("summaryDetails") != null) {
                SummaryFragment().onAttach(requireActivity())
                summaryModelData = arguments?.getSerializable("summaryDetails") as SummaryPassModel
                Log.e("summaryDetails=", summaryModelData.toString())
            }
            for (i in diagnosisArray) {
                if (i.isItemChecked) {
                    diagnosisDataLearner.add(LearnerDiagnosisData(i.id, 0, 0, i.title, "slug"))
                    diagnosisId.add(i.id.toString())
                }
            }
            Log.e("selecteddianosisarray=", diagnosisArray.toString())
            Log.e("listofdata=", diagnosisDataLearner.toString())
        }

        addviewModel =
            ViewModelProvider(requireActivity(), LearnerViewModelFactory(requireContext())).get(
                LearnerViewModel::class.java
            )

        addviewModel.postEditLearnerResult= MutableLiveData<ResponseHandler<ResponseData<LearnerEditModelResponse>?>>()

        binding.idAddBtn.setOnClickListener {

            summaryModelData.let {
                if (it?.userId!!.isNotEmpty()) {
                    Log.e("EditNewLearner=", "EditLernerDetails+="+ it.userId)

                    val dialog = Dialog(requireContext())
                    dialog.setContentView(
                        LayoutInflater.from(context)
                            .inflate(R.layout.diagnosis_update_lerneritem, null)
                    )
                    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    val btnCancel: MaterialButton = dialog.findViewById(R.id.btnEditCancelLearner)
                    val btnUpdate: MaterialButton = dialog.findViewById(R.id.btnUpdateLearner)
                    btnCancel.setOnClickListener {
                        dialog.cancel()
                    }
                    btnUpdate.setOnClickListener {
                        val updateEditLearnerModel = CreateNewLearnerModel(
                            imgId.toString(),
                            binding.idtxSummaryName.text.toString(),
                            binding.idtxMale.text.toString().toLowerCase(Locale.ROOT),
                            binding.idtxDob.text.toString().replace("DOB :", ""),
                            binding.idtxYear.text.toString(),
                            summaryModelData!!.subscriptinoId.replace("#", ""),
                            diagnosisId,
                            ""
                        )
                        summaryModelData.let {
                            addviewModel.postEditLearnerDetails(it!!.userId, updateEditLearnerModel)
                        }
                        dialog.cancel()

                    }

                    dialog.show()


                } else {
                    Log.e("AddNewLearnerDetails=", "AddNewLearner")
                    addviewModel.addNewLearner(
                        CreateNewLearnerModel(
                            imgId.toString(),
                            binding.idtxSummaryName.text.toString(),
                            binding.idtxMale.text.toString().toLowerCase(Locale.ROOT),
                            binding.idtxDob.text.toString(),
                            binding.idtxYear.text.toString(),
                            summaryModelData!!.subscriptinoId.replace("#", ""),
                            diagnosisId,
                            ""
                        )
                    )
                }
            }


        }

        addviewModel.postEditLearnerResult.observe(viewLifecycleOwner) { state ->

            when (state) {
                is ResponseHandler.Loading -> {
                    constDialog.show()
                }
                is ResponseHandler.OnFailed -> {
                    constDialog.cancel()
                }
                is ResponseHandler.OnSuccessResponse<ResponseData<LearnerEditModelResponse>?> ->
                {
                    constDialog.cancel()

                    Log.e("responseEditData->", state.response?.data?.uuid.toString())
                    //Constant.customDiagnosis(requireActivity(),resources.getString(R.string.learnerDataUpdateTitle))
                    val dianosisDialog=Dialog(requireContext())
                    dianosisDialog.setContentView(LayoutInflater.from(context).inflate(R.layout.custom_dialogbox_diagnosis,null))
                    val btnOk:MaterialButton=dianosisDialog.findViewById(R.id.idbtnBlue)
                    val txTitle: MaterialTextView =dianosisDialog.findViewById(R.id.idtxDiagnosisTitle)
                    txTitle.text=resources.getString(R.string.learnerDataUpdateTitle)
                    btnOk.setOnClickListener {
                         dianosisDialog.dismiss()
                         findNavController().navigate(R.id.action_addNewLearnerFragment_to_learnersFragment2)
                    }
                    dianosisDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    dianosisDialog.show()
                }
            }
        }

        addviewModel.resultNewLearner.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseHandler.Loading -> {
                    constDialog.show()
                }
                is ResponseHandler.OnFailed -> {
                    constDialog.cancel()
                }
                is ResponseHandler.OnSuccessResponse<ResponseData<AddNewLearnerResponse>?> -> {

                    constDialog.cancel()
                    Toast.makeText(requireContext(), state.response?.data?.data.toString(), Toast.LENGTH_SHORT).show()
                    Log.e("responseAddLearnerData=", state.response?.data?.data.toString())

                }
            }
        }

        return binding.root
    }

}