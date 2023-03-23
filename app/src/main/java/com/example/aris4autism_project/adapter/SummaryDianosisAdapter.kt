package com.example.aris4autism_project.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.SummaryDianosisLayoutBinding
import com.example.aris4autism_project.model.learnermodel.LearnerDiagnosisData

class SummaryDianosisAdapter(var slist:ArrayList<LearnerDiagnosisData>):RecyclerView.Adapter<SummaryDianosisAdapter.Dianosis>() {

    class Dianosis(val binding : SummaryDianosisLayoutBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item:LearnerDiagnosisData)
        {
            binding.bindTitle=item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Dianosis
    {
        return Dianosis(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.summary_dianosis_layout,parent,false))
    }

    override fun onBindViewHolder(holder: Dianosis, position: Int) 
    {
        Log.e("position",slist.get(position).diagnosisTitle)
        holder.bind(slist.get(position))
    }

    override fun getItemCount(): Int {
       return slist.size
    }
}