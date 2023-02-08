package com.example.aris4autism_project

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.aris4autism_project.Utils.Constant
import com.example.aris4autism_project.databinding.ActivityMain2Binding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var buttonView: BottomNavigationView
    lateinit var includeLayout:View
    lateinit var binding:ActivityMain2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main2);

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        buttonView=binding.bottomNavigation
        includeLayout=findViewById(R.id.idDataLayout)
        includeLayout.visibility=View.GONE

        binding.idDataLayout.idPerson.setOnClickListener {

                val preferences: SharedPreferences =
                    getSharedPreferences(Constant.TokenData, Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = preferences.edit()
                editor.clear()
                editor.apply()
                Toast.makeText(this, "Logout Successfully", Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.singInFragment)

        }

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.learnersFragment,R.id.subscriptionFragment,R.id.overviewFragment,R.id.subuserFragment
            ), drawerLayout = null)

//        setupActionBarWithNavController(navController,appBarConfiguration)
        buttonView.setupWithNavController(navController)

        buttonView.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.learnersFragment2 -> {
                    val txLabel=includeLayout.findViewById<TextView>(R.id.txLabel)
                    txLabel.text="LEARNERS"
                    navController.navigate(R.id.learnersFragment2)
                }
                R.id.subuserFragment2 -> {
                    val txLabel=includeLayout.findViewById<TextView>(R.id.txLabel)
                    txLabel.text="SUBUSER"
                    navController.navigate(R.id.subuserFragment2)
                }
                R.id.overviewFragment2 -> {
                    val txLabel=includeLayout.findViewById<TextView>(R.id.txLabel)
                    txLabel.text="OVERVIEW"
                    navController.navigate(R.id.overviewFragment2)
                }
                R.id.subscriptionFragment2 -> {
                    val txLabel=includeLayout.findViewById<TextView>(R.id.txLabel)
                    txLabel.text="SUBSCRIPTION"
                    navController.navigate(R.id.subscriptionFragment2)
                }
            }
            true
        }

    }
}