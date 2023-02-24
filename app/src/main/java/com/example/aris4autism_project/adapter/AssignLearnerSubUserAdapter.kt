package com.example.aris4autism_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.EditSubuserAssignLearnerLayoutBinding
import com.example.aris4autism_project.model.LearnerIdXXX

class AssignLearnerSubUserAdapter(val list: ArrayList<LearnerIdXXX>):RecyclerView.Adapter<AssignLearnerSubUserAdapter.EditSubUserAssignLearner>() {

    lateinit var binding:EditSubuserAssignLearnerLayoutBinding

    class EditSubUserAssignLearner(val binding:EditSubuserAssignLearnerLayoutBinding):RecyclerView.ViewHolder(binding.root)
    {
                fun bind(AssignModel: LearnerIdXXX)
                {
                    binding.learnerModel=AssignModel
                }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditSubUserAssignLearner {
        binding=DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.edit_subuser_assign_learner_layout,parent,false)
        return EditSubUserAssignLearner(binding)
    }

    override fun onBindViewHolder(holder: EditSubUserAssignLearner, position: Int) {
         val itemData=list[position]
        holder.bind(itemData)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}