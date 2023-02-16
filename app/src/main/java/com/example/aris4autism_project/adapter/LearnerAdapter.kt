package com.example.aris4autism_project.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.LearnerItemsBinding
import com.example.aris4autism_project.model.DataXXXXX

class LearnerAdapter(var slist: List<DataXXXXX>):RecyclerView.Adapter<LearnerAdapter.viewHolder>() {

    val bundle=Bundle()
    lateinit var binding:LearnerItemsBinding

    class viewHolder(val binding:LearnerItemsBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun bind(learnerModel: DataXXXXX)
        {
            binding.bindLearner = learnerModel
        }
        val mtvCard:CardView=binding.mtvCardView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

         binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.learner_items,
                parent,
                false
            )

        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val userbind = slist[position]
        holder.bind(userbind)

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


