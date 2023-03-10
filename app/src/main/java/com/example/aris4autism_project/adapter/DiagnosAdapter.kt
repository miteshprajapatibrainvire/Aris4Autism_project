package com.example.aris4autism_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.DignosisLayoutItemBinding
import com.example.aris4autism_project.fragment.SummaryFragment
import com.example.aris4autism_project.model.LearnerDiagnosisData


class DiagnosAdapter(var slist: ArrayList<LearnerDiagnosisData>?):RecyclerView.Adapter<DiagnosAdapter.DiagnosView>() {

    class DiagnosView(val binding:DignosisLayoutItemBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun positionItem(item:LearnerDiagnosisData)
        {
            binding.bindTitle=item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiagnosView {
        return DiagnosView(DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.dignosis_layout_item,parent,false))
    }

    override fun onBindViewHolder(holder: DiagnosView, position: Int) {
        SummaryFragment().diagnosisId.add(slist!!.get(position).id.toString())
        holder.positionItem(slist!!.get(position))

    }

    override fun getItemCount(): Int {
        return slist!!.size
    }

}