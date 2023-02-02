package com.example.aris4autism_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.aris4autism_project.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var buttonView: BottomNavigationView
    lateinit var includeLayout:View
    lateinit var binding:FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        buttonView=findViewById(R.id.bottom_navigation)
        includeLayout=findViewById(R.id.idDataLayout)
        includeLayout.visibility=View.GONE

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.learnersFragment,R.id.subscriptionFragment,R.id.overviewFragment,R.id.subuserFragment
            ), drawerLayout = null)

//        setupActionBarWithNavController(navController,appBarConfiguration)
        buttonView.setupWithNavController(navController)

        buttonView.setOnItemSelectedListener { item ->

            when (item.itemId) {

                R.id.learnersFragment2 -> {
                    navController.navigate(R.id.learnersFragment2)
                }
                R.id.subuserFragment2 -> {
                    navController.navigate(R.id.subuserFragment2)
                }
                R.id.overviewFragment2 -> {
                    navController.navigate(R.id.overviewFragment2)
                }
                R.id.subscriptionFragment2 -> {
                    navController.navigate(R.id.subscriptionFragment2)
                }

            }
            true
        }

    }
}