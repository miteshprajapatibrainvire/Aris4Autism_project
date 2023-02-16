package com.example.aris4autism_project.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

import androidx.viewpager2.adapter.FragmentStateAdapter


class TabAdapter(
    activity:FragmentActivity
) :
    FragmentStateAdapter(activity) {

    private val fragmentList: MutableList<Fragment> = ArrayList()
    private val fragmentTitleList: MutableList<String> = ArrayList()

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
}