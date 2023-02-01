package com.example.aris4autism_project.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment : Fragment() {

    lateinit var viewpager:ViewPager2
    lateinit var bottomNav:BottomNavigationView
    lateinit var navController: NavController

//    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        when (item.itemId) {
//
//            R.id.learnersId -> {
//                viewpager.currentItem = 0
//                underlineSelectedItem(bottomNav,R.id.learnersId)
//                return@OnNavigationItemSelectedListener true
//            }
//
//            R.id.subuserId -> {
//                viewpager.currentItem = 1
//                underlineSelectedItem(bottomNav,R.id.subuserId)
//                return@OnNavigationItemSelectedListener true
//            }
//
//            R.id.overviewId->{
//                viewpager.currentItem=2
//                underlineSelectedItem(bottomNav,R.id.overviewId)
//                return@OnNavigationItemSelectedListener true
//            }
//
//            R.id.subscriptionId->{
//                viewpager.currentItem=3
//                underlineSelectedItem(bottomNav,R.id.subscriptionId)
//                return@OnNavigationItemSelectedListener true
//            }
//
//        }
//        false
//    }

//    private fun underlineSelectedItem(bottomNavigationView: BottomNavigationView, selectedID: Int)
//    {
//        for (i in 0 until bottomNavigationView.menu.size()) {
//            val menuItem: MenuItem = bottomNavigationView.menu.getItem(i)
//            menuItem.setTitle(menuItem.getTitle().toString().replace("[<u>/]", ""))
//        }
//        val menuItem: MenuItem = bottomNavigationView.menu.findItem(selectedID)
//        val currentText = menuItem.getTitle() as String
//        val convertedText = "<u>$currentText</u>"
//        menuItem.setTitle(Html.fromHtml(convertedText))
//    }
//    private fun getItemPosition(itemId: Int): Int {
//        return when (itemId) {
//            R.id.learnersId -> 0
//            R.id.subuserId -> 1
//            R.id.overviewId -> 2
//            R.id.subscriptionId -> 3
//            else -> 0
//        }
//    }

    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var binding:FragmentMainBinding
    lateinit var navHostFragment:NavHostFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        bottomNav=binding.bottomNavigation

        val navHostFragment =
            activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerViewid) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.learnersFragment,R.id.subscriptionFragment,R.id.overviewFragment,R.id.subuserFragment
            ),
            drawerLayout = null)

        binding.bottomNavigation.setupWithNavController(navController)

        val callback=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)

        return binding.root
    }





}