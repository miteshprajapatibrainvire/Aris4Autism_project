package com.example.aris4autism_project.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.ProfileAdapter
import com.example.aris4autism_project.databinding.FragmentProfileDetailsBinding
import com.example.aris4autism_project.model.ProfileModel
import com.example.aris4autism_project.viewmodel.ProfileDetailViewModel
import com.example.aris4autism_project.viewmodel.ProfileDetailViewModelFactory
import java.util.*

class ProfileDetailsFragment : Fragment() {

    lateinit var adpProfile: ProfileAdapter
    lateinit var binding:FragmentProfileDetailsBinding
    private var GenArray=ArrayList<String>()
    lateinit var genSelect:String
    lateinit var dobSelect:String
    lateinit var viewModel:ProfileDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding= FragmentProfileDetailsBinding.inflate(inflater)
//        iconsList()

        viewModel=ViewModelProvider(requireActivity(),ProfileDetailViewModelFactory(requireContext())).get(ProfileDetailViewModel::class.java)

        viewModel.resultProfile.observe(requireActivity()) { result ->

            if (result.toString().equals(R.string.enterfullaname))
            {
                Log.e("result=", result.toString())
            }

            if (result.toString().equals(R.string.entermobile))
            {
                Log.e("result=", result.toString())
            }

            if (result.toString().equals("valid Data"))
            {
                Log.e("result=", result.toString())
            }

        }

        GenArray.add("Male")
        GenArray.add("Female")
        GenArray.add("Prefer not to say")
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1, GenArray
        )

        binding.spGender.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, arg1: View?, position: Int, arg3: Long) {
                val item = parent.getItemAtPosition(position)
                genSelect=item.toString()
            }
        })

        binding.spGender.setAdapter(adapter)

        binding.dobEd.setOnClickListener {
            clickDatePicker()
        }

        return binding.root
    }

    private fun clickDatePicker()
    {
        val myCalander = Calendar.getInstance()
        val year = myCalander.get(Calendar.YEAR)
        val month = myCalander.get(Calendar.MONTH)
        val day = myCalander.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { datePicker, y, m, d ->
                val monthData = m + 1
                val strData: String = d.toString() + "/" + monthData.toString() + "/" + y.toString()
                dobSelect= strData
                binding.dobEd.setText(strData)
            },
            year,
            month,
            day)

        dpd.datePicker.maxDate = System.currentTimeMillis() - 8640000
        dpd.show()

    }


    private fun iconsList()
    {
        val arrayProfile = ArrayList<ProfileModel>()
        arrayProfile.add(ProfileModel(R.drawable.profileimg1, false))
        arrayProfile.add(ProfileModel(R.drawable.profile2img, false))
        arrayProfile.add(ProfileModel(R.drawable.profileimg3, false))
        arrayProfile.add(ProfileModel(R.drawable.profileimg4, false))
        arrayProfile.add(ProfileModel(R.drawable.profileimggirl1, false))
        arrayProfile.add(ProfileModel(R.drawable.profilegirlimg2, false))
        arrayProfile.add(ProfileModel(R.drawable.profilegirlimg3, false))
        arrayProfile.add(ProfileModel(R.drawable.profileimggirl4, false))
//        adpProfile = ProfileAdapter(arrayProfile)
//        binding.recyIdProfile.layoutManager = GridLayoutManager(requireActivity(), 4)
//        binding.recyIdProfile.adapter = adpProfile

    }

}