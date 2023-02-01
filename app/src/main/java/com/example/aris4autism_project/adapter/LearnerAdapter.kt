package com.example.aris4autism_project.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.LearnerItemsBinding
import com.example.aris4autism_project.model.LearnerModel

class LearnerAdapter(var slist:ArrayList<LearnerModel>):RecyclerView.Adapter<LearnerAdapter.viewHolder>() {

    class viewHolder(binding:LearnerItemsBinding):RecyclerView.ViewHolder(binding.root)
    {
        val txName:TextView=binding.txIdName
        val txIdGender:TextView=binding.txIdGender
        val txYear:TextView=binding.IdYearly
        val dobId:TextView=binding.dobId
        val txMonthPlan:TextView=binding.txIdMonthPlan
        val txFullDate:TextView=binding.txFullDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(LearnerItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.txName.text=slist.get(position).name
        holder.txIdGender.text=slist.get(position).gen
        holder.txYear.text=slist.get(position).yearDetails
        holder.dobId.text=slist.get(position).dob
        holder.txMonthPlan.text=slist.get(position).monthPlan
        holder.txFullDate.text=slist.get(position).startToEndDob
    }

    override fun getItemCount(): Int {
        return slist.size
    }

}