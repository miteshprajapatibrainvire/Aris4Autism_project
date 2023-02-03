package com.example.aris4autism_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.InnerSubDetailsBinding
import com.example.aris4autism_project.databinding.LearnerItemsBinding
import com.example.aris4autism_project.model.LearnerModel

class SubUserInnerDetail(val list:ArrayList<LearnerModel>) {


   lateinit var  binding:InnerSubDetailsBinding

    class viewHolder(binding: InnerSubDetailsBinding): RecyclerView.ViewHolder(binding.root)
    {
        val imgIcon: ImageView =binding.imgIdIcon
        val txName: TextView =binding.txIdName
        val txIdGender: TextView =binding.txIdGender
        val txYear: TextView =binding.IdYearly
        val dobId: TextView =binding.dobId
        val txMonthPlan: TextView =binding.txIdMonthPlan
        val txFullDate: TextView =binding.txFullDate

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
//        binding= DataBindingUtil.inflate(
//            LayoutInflater.from(parent.context),
//            R.layout.inner_sub_details,
//            parent,
//            false)
//
//        return viewHolder(binding)

    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

//        holder.txName.text=list.get(position).name
//        holder.txIdGender.text=list.get(position).gen
//        holder.txYear.text=list.get(position).yearDetails
//        holder.dobId.text=list.get(position).dob
//        holder.txMonthPlan.text=list.get(position).monthPlan
//        holder.txFullDate.text=list.get(position).startToEndDob
//        holder.imgIcon.setImageResource(list.get(position).imgId)

    }

    override fun getItemCount(): Int {
//        return list.size
    }


     */

}