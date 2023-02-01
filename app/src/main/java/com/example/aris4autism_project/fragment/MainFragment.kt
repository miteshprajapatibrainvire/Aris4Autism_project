package com.example.aris4autism_project.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarItemView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener


class MainFragment : Fragment()/*, OnNavigationItemSelectedListener*/ {

    lateinit var viewpager:ViewPager2
    lateinit var bottomNav:BottomNavigationView
    lateinit var navController: NavController


    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var binding:FragmentMainBinding
    lateinit var navHostFragment:NavHostFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)

       /* bottomNav=binding.bottomNavigation

//        navController=findNavController(R.id.fragmentContainerViewid)
//        navHostFragment=activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerViewid) as NavHostFragment

        navController =findNavController()

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.learnersFragment,R.id.subscriptionFragment,R.id.overviewFragment,R.id.subuserFragment
            ), drawerLayout = null)

//       setupActionBarWithNavController(navController,appBarConfiguration)
        binding.bottomNavigation.setupWithNavController(navController)
        NavigationUI.setupWithNavController(bottomNav, navController)

        val callback=object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->

            when (item.itemId) {

                R.id.learnersFragment -> {
                    navController.navigate(LearnersFragmentDirections.actionLearnersFragmentToSubscriptionFragment())
                }

                R.id.subscriptionFragment -> {
//                    navController.navigate(R.id.subscriptionFragment)
                    /*Toast.makeText(requireContext(),  navController.currentDestination?.label.toString()
                        , Toast.LENGTH_SHORT).show()*/
                   /* navController.navigate(R.id.subuserFragment)*/

                }

                R.id.overviewFragment -> {
                    navController.navigate(R.id.overviewFragment)
                }

                R.id.subuserFragment -> {
                    navController.navigate(R.id.subuserFragment)
                }

            }
            true
        }

        /*binding.bottomNavigation.setOnItemSelectedListener {

                if(it.itemId==R.id.subuserFragment){
                    navController.navigate(R.id.action_subscriptionFragment_to_overviewFragment)
                     true
                }
             false

        }*/
        requireActivity().onBackPressedDispatcher.addCallback(callback)
        //binding.bottomNavigation.
        */

        return binding.root
    }


    /*override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.learnersFragment->{
                navController.navigate(R.id.action_learnersFragment_to_subscriptionFragment)
            }

            R.id.subscriptionFragment->{
                navController.navigate(R.id.action_subscriptionFragment_to_overviewFragment)
            }

            R.id.overviewFragment->{
                navController.navigate(R.id.action_overviewFragment_to_subuserFragment)
            }
            R.id.subuserFragment->
            {
                navController.navigate(R.id.action_subuserFragment_to_learnersFragment)
            }

        }

        NavigationUI.setupWithNavController(bottomNav, navController)
        return NavigationUI.onNavDestinationSelected(item,navController)
    }*/



}