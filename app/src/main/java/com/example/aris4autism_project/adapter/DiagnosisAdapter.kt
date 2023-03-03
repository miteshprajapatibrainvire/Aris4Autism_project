package com.example.aris4autism_project.adapter

import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.util.size
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.DignosisSelectLayoutBinding
import com.example.aris4autism_project.fragment.DiagnosisFragment
import com.example.aris4autism_project.model.DataXXXXXXXXXXXXXXXXXXXXXXXXX
import com.example.aris4autism_project.model.GetDiagnosisData
import com.google.android.material.checkbox.MaterialCheckBox

class DiagnosisAdapter(var slist:ArrayList<DataXXXXXXXXXXXXXXXXXXXXXXXXX>
,var getDianosis:(checkedState:Boolean)->Unit,var checkBorder:String):RecyclerView.Adapter<DiagnosisAdapter.DiagnosisViewData>() {

    var dataCheckedList=ArrayList<MaterialCheckBox>()
    var checkData:String = checkBorder
    private var checkBoxState = SparseBooleanArray()
    private var isSelectedAll = false

    class DiagnosisViewData(val binding: DignosisSelectLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
    {
        fun bind(item: DataXXXXXXXXXXXXXXXXXXXXXXXXX) {
            binding.bindDiagnosis = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiagnosisViewData
    {
        return DiagnosisViewData(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.dignosis_select_layout,parent,false))
    }

    override fun onBindViewHolder(holder: DiagnosisViewData, position: Int) {

        dataCheckedList.add(holder.binding.idDiagnosCheckBox)

        //holder.binding.idDiagnosCheckBox.isChecked = checkBoxState.get(position, false)


            holder.bind(slist[position])

            if(checkBorder.equals(checkData))
            {
                holder.binding.idConstarintLayoutDiagnosis.setBackgroundResource(R.drawable.diagnosis_red_border)
                holder.binding.idtxDiagnosisTitle.setTextColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.darkblue
                    )
                )
                checkBoxState.append(position,false)
            }

//        if(holder.binding.idDiagnosCheckBox.isChecked)
//        {
//            holder.binding.idConstarintLayoutDiagnosis.setBackgroundResource(R.drawable.bg_light_blue)
//            if(!holder.binding.idDiagnosCheckBox.isChecked)
//            {
//                holder.binding.idConstarintLayoutDiagnosis.setBackgroundResource(R.drawable.borderimgicon)
//            }
//
//        }


//        dataCheckedList.add(holder.binding.idDiagnosCheckBox)

        holder.binding.idDiagnosCheckBox.setOnClickListener {

           notifyItemChanged(position)
            checkData="redBorderGone"
           // notifyDataSetChanged()
            if(holder.binding.idDiagnosCheckBox.isChecked)
            {
                getDianosis(true)
                holder.binding.idDiagnosCheckBox.isChecked=true
                DiagnosisFragment.diagnosisArray.add(
                    GetDiagnosisData(0,slist.get(position).title,0,0,""))
                holder.binding.idConstarintLayoutDiagnosis.setBackgroundResource(R.drawable.bg_light_blue)
                holder.binding.idtxDiagnosisTitle.setTextColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.darkblue
                    )
                )

                var holdCheckbox=holder.binding.idDiagnosCheckBox
                for( i in dataCheckedList)
                {
                    if(i==holder.binding.idDiagnosCheckBox)
                    {
                        holder.binding.idConstarintLayoutDiagnosis.setBackgroundResource(R.drawable.bg_light_blue)
                        holder.binding.idtxDiagnosisTitle.setTextColor(
                            ContextCompat.getColor(
                                holder.itemView.context,
                                R.color.darkblue
                            )
                        )
                    }
                    else
                    {
                        holder.binding.idConstarintLayoutDiagnosis.setBackgroundResource(R.drawable.borderimgicon)
                    }
                    notifyDataSetChanged()
                }
                checkBoxState.append(position,true)
            }
            else
            {
                var ps:Int=0
                checkBoxState.append(position,false)
                for(i in DiagnosisFragment.diagnosisArray)
                {
                    if(i.diagnosisTitle.equals(slist.get(position).title))
                    {
                        DiagnosisFragment.diagnosisArray.remove(i)
                    }
                    ps++
                }
                getDianosis(false)
                holder.binding.idDiagnosCheckBox.isChecked=false
                holder.binding.idConstarintLayoutDiagnosis.setBackgroundResource(R.drawable.borderimgicon)
                holder.binding.idtxDiagnosisTitle.setTextColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.gray
                    )
                )
                //Toast.makeText(holder.itemView.context, slist.get(position).title, Toast.LENGTH_SHORT).show()
            }
            Log.e("DiagnosisData=",DiagnosisFragment.diagnosisArray.toString())
        }
    }

    override fun getItemCount(): Int {
       return slist.size
    }
}