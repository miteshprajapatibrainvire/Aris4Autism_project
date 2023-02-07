package com.example.aris4autism_project.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.LearnerItemsBinding
import com.example.aris4autism_project.model.DataXXXXX
import java.text.SimpleDateFormat
import java.util.*

class LearnerAdapter(var context: Context, var slist: List<DataXXXXX>):RecyclerView.Adapter<LearnerAdapter.viewHolder>() {

    val bundle=Bundle()
    var calendar: Calendar = Calendar.getInstance()

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

        return viewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.learner_items,
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.txName.text=slist.get(position).name
        holder.txIdGender.text=slist.get(position).gender

//        val year = calendar[Calendar.YEAR]
//        val month = calendar[Calendar.MONTH]
//        val day = calendar[Calendar.DAY_OF_MONTH]
//        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
//        val date: String = simpleDateFormat.format(Calendar.getInstance().time)

        holder.txYear.text=slist.get(position).age.toString()
        holder.dobId.text="DOB : "+slist.get(position).dateOfBirth
        holder.txMonthPlan.text="#"+slist.get(position).subscriptionId.toString() + " - " + slist.get(position).userSubscriptions.title
        holder.txFullDate.text=slist.get(position).userSubscriptions.startDate+" to "+slist.get(position).userSubscriptions.endDate
//        holder.imgIcon.setImageResource(slist.get(position).imgId)
        Glide.with(context)
            .load(slist.get(position).getLearnerIcon.iconUrl)
            .into(holder.imgIcon)


        if(slist.get(position).userSubscriptions.status!="active")
        {
            holder.txActive.setBackgroundResource(R.drawable.status_expired_tag)
            holder.txActive.text="Expired"
        }

        holder.mtvCard.setOnClickListener {view->

            val passModel:DataXXXXX=slist.get(position)

            bundle.putString("name",passModel.name)
            bundle.putString("gender",passModel.gender)
         bundle.putString("age",passModel.age.toString())
            bundle.putString("dob",passModel.dateOfBirth)
            bundle.putString("subscriptionId",passModel.subscriptionId.toString())
            bundle.putString("monthlyplan",passModel.userSubscriptions.title)
            bundle.putString("starttoenddob",passModel.userSubscriptions.startDate+" to "+slist.get(position).userSubscriptions.endDate)
            bundle.putString("activeStatus",passModel.userSubscriptions.status)
            bundle.putString("startDob",passModel.userSubscriptions.startDate)
            bundle.putString("endDob",passModel.userSubscriptions.endDate)
            bundle.putString("iconImg",slist.get(position).getLearnerIcon.iconUrl)
            bundle.putSerializable("diagnotsisArray",passModel.getDiagnosisData)

            view.findNavController().navigate(R.id.action_learnersFragment2_to_learnerDetailsFragment,bundle)

        }

    }

    override fun getItemCount(): Int {
        return slist.size
    }


}


