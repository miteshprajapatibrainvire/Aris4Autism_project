package com.example.aris4autism_project.stepperdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class StepperAdp(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity)
{

    private val fragmentList: MutableList<Fragment> = ArrayList()
    private val fragmentTitleList: MutableList<String> = ArrayList()
    private  var fragments:ArrayList<ITabbedFragment> = ArrayList()
    var bundle:Bundle=Bundle()

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }

    override fun getItemCount(): Int {
        return  fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

//    override fun sendArrayListData(sm: String, fragment: Fragment) {
//        bundle.putString("strData",sm)
//      for(i in fragmentList)
//      {
//          if(i.equals(fragment))
//          {
//              i.arguments=bundle
//              break
//          }
//      }
//    }
//
//    override fun onReceice(obj: Fragment) {
//
//    }

//    override fun sendArrayListData(Data: ArrayList<String>) {
//    }
}