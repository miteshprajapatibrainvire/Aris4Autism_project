package com.example.aris4autism_project.adapter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.Fragment




class MainAdapter( activity: FragmentActivity?
) : FragmentStateAdapter(activity!!) {

    private val mFragmentList: MutableList<Fragment> = ArrayList()

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
    }

    override fun getItemCount(): Int {
        return mFragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return mFragmentList[position]
    }

}