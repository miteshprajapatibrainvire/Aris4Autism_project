package com.example.aris4autism_project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.DignosisSelectLayoutBinding
import com.example.aris4autism_project.model.diagnosismodel.DiagnosisDetailResponseModel

class DiagnosisAdapter(
    var slist: List<DiagnosisDetailResponseModel>?,
    var getDianosis: (checkedState: Boolean) -> Unit,
    var checkBorder: String
) : RecyclerView.Adapter<DiagnosisAdapter.DiagnosisViewData>() {

    var firstAttempCheckBox: Boolean = false
    var holderArray = ArrayList<AppCompatCheckBox>()
    var holderConstraint = ArrayList<ConstraintLayout>()

    class DiagnosisViewData(val binding: DignosisSelectLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DiagnosisDetailResponseModel) {
            binding.bindDiagnosis = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiagnosisViewData {
        return DiagnosisViewData(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.dignosis_select_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DiagnosisViewData, position: Int) {

        holder.bind(slist!![position])
//        holderArray.add(holder.binding.idDiagnosCheckBox)
//        holderConstraint.add(holder.binding.idConstarintLayoutDiagnosis)

        /* if(checkBorder.equals("changeBorder"))
         {
             if(!holder.binding.idDiagnosCheckBox.isChecked)
             {
                 holder.binding.idConstarintLayoutDiagnosis.background =
                     ContextCompat.getDrawable(holder.itemView.context, R.drawable.diagnosis_red_border)
             }
         }*/

        /*if(checkBorder.equals("checkeditem"))
        {
            if(holder.binding.idDiagnosCheckBox.isChecked)
            {
                holder.binding.idConstarintLayoutDiagnosis.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.borderimgicon)
            }
        }

        if(holder.binding.idDiagnosCheckBox.isChecked)
        {
            holder.binding.idConstarintLayoutDiagnosis.background=ContextCompat.getDrawable(holder.itemView.context,R.drawable.bg_light_blue)
            holder.binding.idDiagnosCheckBox.isChecked=true
        }*/

        if (slist!!.get(position).isNoItemChecked) {
            holder.binding.idConstarintLayoutDiagnosis.background =
                ContextCompat.getDrawable(holder.itemView.context, R.drawable.diagnosis_red_border)
        } else {
            if (slist!![position].isItemChecked) {
                holder.binding.idConstarintLayoutDiagnosis.background =
                    ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_light_blue)
                holder.binding.idDiagnosCheckBox.isChecked = true
            } else {
                holder.binding.idConstarintLayoutDiagnosis.background =
                    ContextCompat.getDrawable(holder.itemView.context, R.drawable.borderimgicon)
                holder.binding.idDiagnosCheckBox.isChecked = false
            }
        }


//        if(!holder.binding.idCheckbox.isChecked)
//        {
//            getSelected(false)
//        }

        holder.binding.idDiagnosCheckBox.setOnClickListener {
            if (holder.binding.idDiagnosCheckBox.isChecked) {
                for (i in slist!!.indices) {
                    slist!![i].isNoItemChecked = false
                }

                // add true
                slist?.let {
                    it[position].isItemChecked = true
                    getDianosis(true)
                }
            } else {
                // add false
                slist?.let {
                    it[position].isItemChecked = false
                    getDianosis(false)
                }
            }
            notifyDataSetChanged()
        }
        /* holder.binding.idDiagnosCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->

             holderArray.add(position,holder.binding.idDiagnosCheckBox)
             holderConstraint.add(position,holder.binding.idConstarintLayoutDiagnosis)
             if(isChecked)
             {
                 DiagnosisFragment.diagnosisArray.add(
                     DiagnosisInnerData(slist!!.get(position).id,slist!!.get(position).title,"")
                 )
                 holder.binding.idDiagnosCheckBox.isChecked = true
                 //Toast.makeText(holder.itemView.context, "checkedStateTrue", Toast.LENGTH_SHORT).show()
                 holder.binding.idConstarintLayoutDiagnosis.background =
                     ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_light_blue)
                 getDianosis(true)
                 //  notifyItemChanged(position)
                 Log.e("checked=",DiagnosisFragment.diagnosisArray.toString())
             }
             else{
                 var ps:Int=0
                 for(i in DiagnosisFragment.diagnosisArray)
                 {
                     if(i.title.equals(slist!!.get(position).title))
                     {
                         DiagnosisFragment.diagnosisArray.remove(i)
                         break
                     }
                     ps++
                 }

                 Log.e("unchecked=",DiagnosisFragment.diagnosisArray.toString())

                 //Toast.makeText(holder.itemView.context, "checkedStateFalse", Toast.LENGTH_SHORT).show()
                 holder.binding.idDiagnosCheckBox.isChecked = false
                 holder.binding.idConstarintLayoutDiagnosis.background =
                     ContextCompat.getDrawable(holder.itemView.context, R.drawable.borderimgicon)
             }
             for (i in holderArray.indices) {
                 if (!holderArray.get(i).isChecked)
                 {
                     holderArray.get(i).isChecked = false
                     holderConstraint.get(i).background = ContextCompat.getDrawable(
                         holder.itemView.context,
                         R.drawable.borderimgicon
                     )
                     if(firstAttempCheckBox)
                     {
 //                        notifyItemChanged(position)
                         firstAttempCheckBox=true
                     }
                 }
             }
         }*/


        // dataCheckedList.add(holder.binding.idDiagnosCheckBox)
        //holder.binding.idDiagnosCheckBox.isChecked = checkBoxState.get(position, false)
//            holder.bind(slist[position])
//
//            if(checkBorder.equals(checkData))
//            {
//                holder.binding.idConstarintLayoutDiagnosis.setBackgroundResource(R.drawable.diagnosis_red_border)
//                holder.binding.idtxDiagnosisTitle.setTextColor(
//                    ContextCompat.getColor(
//                        holder.itemView.context,
//                        R.color.darkblue
//                    )
//                )
//                checkBoxState.append(position,false)
//            }
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
        //holder.binding.idDiagnosCheckBox.setOnClickListener {
//           notifyItemChanged(position)
//            checkData="redBorderGone"
//           // notifyDataSetChanged()
//            if(holder.binding.idDiagnosCheckBox.isChecked)
//            {
//                getDianosis(true)
//                holder.binding.idDiagnosCheckBox.isChecked=true
//                DiagnosisFragment.diagnosisArray.add(
//                    GetDiagnosisData(0,slist.get(position).title,0,0,""))
//                holder.binding.idConstarintLayoutDiagnosis.setBackgroundResource(R.drawable.bg_light_blue)
//                holder.binding.idtxDiagnosisTitle.setTextColor(
//                    ContextCompat.getColor(
//                        holder.itemView.context,
//                        R.color.darkblue
//                    )
//                )
//                var holdCheckbox=holder.binding.idDiagnosCheckBox
//                for( i in dataCheckedList)
//                {
//                    if(i==holder.binding.idDiagnosCheckBox)
//                    {
//                        holder.binding.idConstarintLayoutDiagnosis.setBackgroundResource(R.drawable.bg_light_blue)
//                        holder.binding.idtxDiagnosisTitle.setTextColor(
//                            ContextCompat.getColor(
//                                holder.itemView.context,
//                                R.color.darkblue
//                            )
//                        )
//                    }
//                    else
//                    {
//                        holder.binding.idConstarintLayoutDiagnosis.setBackgroundResource(R.drawable.borderimgicon)
//                    }
//                    notifyDataSetChanged()
//                }
//                checkBoxState.append(position,true)
//            }
//            else
//            {
//                var ps:Int=0
//                checkBoxState.append(position,false)
//                for(i in DiagnosisFragment.diagnosisArray)
//                {
//                    if(i.diagnosisTitle.equals(slist.get(position).title))
//                    {
//                        DiagnosisFragment.diagnosisArray.remove(i)
//                    }
//                    ps++
//                }
//                getDianosis(false)
//                holder.binding.idDiagnosCheckBox.isChecked=false
//                holder.binding.idConstarintLayoutDiagnosis.setBackgroundResource(R.drawable.borderimgicon)
//                holder.binding.idtxDiagnosisTitle.setTextColor(
//                    ContextCompat.getColor(
//                        holder.itemView.context,
//                        R.color.gray
//                    )
//                )
//                //Toast.makeText(holder.itemView.context, slist.get(position).title, Toast.LENGTH_SHORT).show()
//            }
//            Log.e("DiagnosisData=",DiagnosisFragment.diagnosisArray.toString())
//        }
    }

    override fun getItemCount(): Int {
        return slist!!.size
    }
}
