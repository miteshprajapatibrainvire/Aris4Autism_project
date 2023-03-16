package com.example.aris4autism_project.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.aris4autism_project.fragment.HowLearnerWorksFragment
import com.example.aris4autism_project.fragment.HowOverviewWorksFragment
import com.example.aris4autism_project.fragment.HowSubUserWorksFragment

class TabLayoutAdapterHowItWork(
    fragmentManager: FragmentManager
): FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
       return when(position)
        {
            0-> HowLearnerWorksFragment("learner")
            1 -> HowSubUserWorksFragment("subuser")
            2 -> HowOverviewWorksFragment("overview")
            else-> getItem(position)
        }
    }


}