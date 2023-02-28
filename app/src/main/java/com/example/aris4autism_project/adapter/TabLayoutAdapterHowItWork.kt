package com.example.aris4autism_project.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.aris4autism_project.fragment.HowLearnerWorksFragment
import com.example.aris4autism_project.fragment.HowOverviewWorksFragment
import com.example.aris4autism_project.fragment.HowSubUserWorksFragment
import com.example.aris4autism_project.model.LearnerXXXXXX

class TabLayoutAdapterHowItWork(
    fragmentManager: FragmentManager
):FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
       return when(position)
        {
            0-> HowLearnerWorksFragment()
            1 -> HowSubUserWorksFragment()
            2 -> HowOverviewWorksFragment()
            else-> getItem(position)
        }
    }


}