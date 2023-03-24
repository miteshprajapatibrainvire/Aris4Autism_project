package com.example.aris4autism_project.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.LearnerItemsBinding
import com.example.aris4autism_project.model.learnermodel.LearnerData

class LearnerAdapter(var slist: ArrayList<LearnerData>):RecyclerView.Adapter<LearnerAdapter.viewHolder>() {

    val bundle=Bundle()
    lateinit var binding:LearnerItemsBinding

    class viewHolder(val binding:LearnerItemsBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun bind(learnerModel: LearnerData)
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

            val passModel:LearnerData=slist.get(position)
            Log.e("passModel=",slist.toString())
            bundle.putString("uuid",passModel.uuid)


            bundle.putString("iconImg",slist.get(position).getLearnerIcon.iconUrl)
            bundle.putString("iconImgId",slist.get(position).getLearnerIcon.id.toString())

           // bundle.putSerializable("diagnosData",passModel.getDiagnosisData)

            view.findNavController().navigate(R.id.action_learnersFragment2_to_learnerDetailsFragment,bundle)
        }
    }

    override fun getItemCount(): Int {
        return slist.size
    }


}


