package com.example.aris4autism_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.aris4autism_project.R
import com.example.aris4autism_project.adapter.MainAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment : Fragment() {

    lateinit var viewpager:ViewPager2
    lateinit var bottomNav:BottomNavigationView


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {

            R.id.learnersId -> {
                viewpager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.subuserId -> {
                viewpager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.overviewId->{
                viewpager.currentItem=2
                return@OnNavigationItemSelectedListener true
            }
            R.id.subscriptionId->{
                viewpager.currentItem=3
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_main, container, false)

        viewpager = view.findViewById(R.id.viewPager)
        bottomNav=view.findViewById(R.id.bottom_navigation)

        val viewAdapter=MainAdapter(activity)

        viewAdapter.addFragment(LearnersFragment(),"Learners")
        viewAdapter.addFragment(SubuserFragment(),"Subuser")
        viewAdapter.addFragment(OverviewFragment(),"Overview")
        viewAdapter.addFragment(SubscriptionFragment(),"Subscription")
        viewpager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL)
        viewpager.adapter=viewAdapter

        bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        return view
    }


}