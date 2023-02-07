package com.example.aris4autism_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.InnerSubDetailsBinding
import com.example.aris4autism_project.model.LearnerModel

class SubUserInnerDetail(val list:ArrayList<LearnerModel>):RecyclerView.Adapter<SubUserInnerDetail.viewHolder>() {


   lateinit var  binding:InnerSubDetailsBinding

    class viewHolder(binding: InnerSubDetailsBinding): RecyclerView.ViewHolder(binding.root)
    {
        val txId:TextView=binding.txIdNum
        val imgIcon: ImageView =binding.imgIdIcon
        val txName: TextView =binding.txIdName
        val txStartDate:TextView=binding.txIdStartDate
        val txEndDate:TextView=binding.txIdEndDate
        val txDob:TextView=binding.idTxMonth
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        binding= DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.inner_sub_details,
            parent,
            false)
        return viewHolder(binding)

    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.imgIcon.setImageResource(list.get(position).imgId)
        holder.txName.text=list.get(position).name
        holder.txStartDate.text=list.get(position).startDob
        holder.txEndDate.text=list.get(position).endDob
        holder.txId.text="#"+list.get(position).id.toString()
        holder.txDob.text=list.get(position).monthPlan

//        holder.txName.text=list.get(position).name
//        holder.txIdGender.text=list.get(position).gen
//        holder.txYear.text=list.get(position).yearDetails
//        holder.dobId.text=list.get(position).dob
//        holder.txMonthPlan.text=list.get(position).monthPlan
//        holder.txFullDate.text=list.get(position).startToEndDob
//        holder.imgIcon.setImageResource(list.get(position).imgId)

    }

    override fun getItemCount(): Int {
        return list.size
    }




}