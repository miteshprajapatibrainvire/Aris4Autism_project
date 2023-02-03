package com.example.aris4autism_project.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.LearnerItemsBinding
import com.example.aris4autism_project.model.DiagnosisModel
import com.example.aris4autism_project.model.LearnerModel

class LearnerAdapter(var context: Context, var slist:ArrayList<LearnerModel>):RecyclerView.Adapter<LearnerAdapter.viewHolder>() {
    val bundle=Bundle()
    class viewHolder(binding:LearnerItemsBinding):RecyclerView.ViewHolder(binding.root)
    {
        val imgIcon:ImageView=binding.imgIdIcon
        val txName:TextView=binding.txIdName
        val txIdGender:TextView=binding.txIdGender
        val txYear:TextView=binding.IdYearly
        val dobId:TextView=binding.dobId
        val txMonthPlan:TextView=binding.txIdMonthPlan
        val txFullDate:TextView=binding.txFullDate
        val txActive:TextView=binding.idActive
        val mtvCard:CardView=binding.mtvCardView
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
        holder.imgIcon.setImageResource(slist.get(position).imgId)

        if(!slist.get(position).activeStatus)
        {
            holder.txActive.setBackgroundResource(R.drawable.status_expired_tag)
            holder.txActive.text="Expired"
        }

        holder.mtvCard.setOnClickListener {view->

            val passModel:LearnerModel=slist.get(position)

            bundle.putString("name",passModel.name)
            bundle.putString("gender",passModel.gen)
            bundle.putString("yearDetail",passModel.yearDetails)
            bundle.putString("dob",passModel.dob)
            bundle.putString("monthlyplan",passModel.monthPlan)
            bundle.putString("starttoenddob",passModel.startToEndDob)
            bundle.putBoolean("activeStatus",passModel.activeStatus)
            bundle.putString("startDob",passModel.startDob)
            bundle.putString("endDob",passModel.endDob)
            bundle.putInt("iconImg",slist.get(position).imgId)
            bundle.putSerializable("diagnotsisArray",passModel.dignosis)

            view.findNavController().navigate(R.id.action_learnersFragment2_to_learnerDetailsFragment,bundle)

        }

    }

    override fun getItemCount(): Int {
        return slist.size
    }


}


