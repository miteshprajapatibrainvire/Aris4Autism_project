@file:Suppress("UNCHECKED_CAST")

package com.example.aris4autism_project.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.aris4autism_project.R
import com.example.aris4autism_project.Utils.CalenderFormat
import com.example.aris4autism_project.Utils.Utils
import com.example.aris4autism_project.adapter.DiagnosAdapter
import com.example.aris4autism_project.databinding.FragmentLearnerDetailsBinding
import com.example.aris4autism_project.model.DataXXXXX
import com.example.aris4autism_project.model.GetDiagnosisData
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class LearnerDetailsFragment : Fragment() {

    lateinit var binding:FragmentLearnerDetailsBinding
    private lateinit var navController: NavController
     private val args : LearnerDetailsFragmentArgs by navArgs()


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding=FragmentLearnerDetailsBinding.inflate(layoutInflater, container, false)

        //set visibility main toolbar
        val view=requireActivity().findViewById<View>(R.id.idDataLayout)
        view.visibility=View.GONE

        //set main toolbar text
        binding.mainLayoutId.txIdMainLabel.text = resources.getString(R.string.learnerdetail)

        //get argument data from learner fragment
        val uuid:String=requireArguments().getString("uuid").toString()
        val name:String = requireArguments().getString("name").toString()
        val gender:String = requireArguments().getString("gender").toString()
        val dob:String=requireArguments().getString("dob").toString()
        val monthlyplan=requireArguments().getString("monthlyplan").toString()
        val activeStatus:String=requireArguments().getString("activeStatus").toString()
        val startDob=requireArguments().getString("startDob").toString()
        val endDob=requireArguments().getString("endDob").toString()
        val diagnosis=requireArguments().getSerializable("diagnotsisArray")
        val subId=requireArguments().getString("subscriptionId").toString()
        val imgIcon:String= requireArguments().getString("iconImg").toString()

        //navhost constroller
        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        //set diagnosis adapter
        binding.recyDiagnosis.adapter=DiagnosAdapter(diagnosis as ArrayList<GetDiagnosisData>?)
        binding.recyDiagnosis.layoutManager= LinearLayoutManager(requireActivity())

        //set learner details which is get frmo learner fragment
        binding.txIdName.text=name
        binding.txIdGender.text=gender
        binding.IdYearly.text=dobToAge(dob)
        binding.dobId.text = "DOB : $dob"
        binding.txidStartData.text=startDob
        binding.txidEndData.text=endDob
        binding.txMonthPlan.text=monthlyplan
        binding.txidSubDetail.text = "#$subId"

        Log.e("imageIcon=", imgIcon)
        //load image in glide library
        Glide.with(requireActivity())
            .load(imgIcon)
            .into(binding.imgIdDetailIcon)

        val bundle=Bundle()
        bundle.putString("uuid",uuid)
        binding.mainLayoutId.idDetailPerson.setOnClickListener{

            val bundle=Bundle()
            bundle.putString("uuid",requireArguments().getString("uuid").toString())
            bundle.putString("name",requireArguments().getString("name").toString())
            bundle.putString("gender",requireArguments().getString("gender").toString())
            bundle.putString("dob",requireArguments().getString("dob").toString())
            bundle.putString("monthlyplan",requireArguments().getString("monthlyplan").toString())
            bundle.putString("activeStatus",requireArguments().getString("activeStatus").toString())
            bundle.putString("startDob",requireArguments().getString("startDob").toString())
            bundle.putString("endDob",requireArguments().getString("endDob").toString())
            bundle.putSerializable("diagnotsisArray",requireArguments().getSerializable("diagnotsisArray"))
            bundle.putString("subscriptionId",requireArguments().getString("subscriptionId").toString())
            bundle.putString("iconImg",requireArguments().getString("iconImg").toString())

            findNavController().navigate(R.id.action_learnerDetailsFragment_to_addNewLearnerFragment,bundle)

        }

        //navigate learnerdetailfragment to learnerfragment
        binding.mainLayoutId.imgMainBack.setOnClickListener {
            findNavController().navigate(R.id.action_learnerDetailsFragment_to_learnersFragment2)
        }

        //backpress  from learnerleatails fragment to learner fragment
        val callback=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_learnerDetailsFragment_to_learnersFragment2)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)

        //set active and expire status for learnerdetails
        if(!activeStatus.equals("active"))
        {
            binding.idActiveDetail.setBackgroundResource(R.drawable.status_expired_tag)
            binding.idActiveDetail.text = "Expired"
            binding.idPurchaseNewSub.visibility=View.VISIBLE
        }
        else {
            //subscription purchase visibility gone
            binding.idPurchaseNewSub.visibility = View.GONE
        }


        return binding.root
    }

    //get dob to age
    private fun dobToAge(dob: String): String
    {
        return if (!Utils.checkDateFormat(dob, CalenderFormat.MM_DD_YYYY_D.type)) {
            val formatter: DateFormat =
                SimpleDateFormat(CalenderFormat.YYYY_MM_DD.type, Locale.ROOT)
            val formatter2: DateFormat =
                SimpleDateFormat(CalenderFormat.MM_DD_YYYY_D.type, Locale.ROOT)
            val date = formatter.parse(dob) as Date
            val date2 = formatter2.format(date)
            Utils.calculateAge(date2)
        }
        else
        {
            Utils.calculateAge(dob)
        }
    }

}