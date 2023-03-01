package com.example.aris4autism_project

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.aris4autism_project.databinding.ActivityMain2Binding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var buttonView: BottomNavigationView
    lateinit var includeLayout: View
    lateinit var binding: ActivityMain2Binding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        navController = navHostFragment.navController
        buttonView = binding.bottomNavigation
        includeLayout = findViewById(R.id.idDataLayout)
        includeLayout.visibility = View.GONE

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.learnersFragment,
                R.id.subscriptionFragment,
                R.id.overviewFragment,
                R.id.subuserFragment
            ), drawerLayout = null
        )

        buttonView.setupWithNavController(navController)

        binding.idDataLayout.idPerson.setOnClickListener {

//            navController.popBackStack(R.id.learnersFragment2,true)
//            navController.popBackStack(R.id.subuserFragment2,true)
//            navController.popBackStack(R.id.overviewFragment2,true)
//            navController.popBackStack(R.id.subscriptionFragment2,true)
            navController.navigate(R.id.userMainFragment)

        }

        buttonView.setOnItemSelectedListener { item ->

            when (item.itemId) {

                R.id.learnersFragment2 ->
                {
                    val txLabel = includeLayout.findViewById<TextView>(R.id.txLabel)
                    txLabel.text = "LEARNERS"
                    navController.navigate(R.id.learnersFragment2)
                }

                R.id.subuserFragment2 ->
                {
                    val txLabel = includeLayout.findViewById<TextView>(R.id.txLabel)
                    txLabel.text = "SUBUSER"
                    navController.navigate(R.id.subuserFragment2)
                }

                R.id.overviewFragment2 ->
                {
                    val txLabel = includeLayout.findViewById<TextView>(R.id.txLabel)
                    txLabel.text = "OVERVIEW"
                    navController.navigate(R.id.overviewFragment2)
                }

                R.id.subscriptionFragment2 ->
                {
                    val txLabel = includeLayout.findViewById<TextView>(R.id.txLabel)
                    txLabel.text = "SUBSCRIPTION"
                    navController.navigate(R.id.subscriptionFragment2)
                }

            }
            true
        }

    }
}