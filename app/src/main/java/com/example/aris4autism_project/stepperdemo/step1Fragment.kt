package com.example.aris4autism_project.stepperdemo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.FragmentStep1Binding


class step1Fragment : Fragment() {

    lateinit var binding: FragmentStep1Binding
    lateinit var sm: SendArrayList

    override fun onAttach( context: Context) {
        super.onAttach(context)

        /*try {
            sm = context  as SendArrayList
        }
        catch (e:Exception)
        {
            Log.e("exception=",e.toString())
        }*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStep1Binding.inflate(inflater)
        val viewpager = activity?.findViewById<ViewPager2>(R.id.idViewpagerData)
        binding.btnStep1.setOnClickListener {view->
//            val dataBoy: SendArrayList? =
            Toast.makeText(requireContext(), "click step1", Toast.LENGTH_SHORT).show()
            val fragment = step2Fragment()
            val bundle = Bundle()

            bundle.putString("username", binding.edtxFirst.text.toString())
            fragment.setArguments(bundle)
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragmentLayout2, fragment)?.commit()
//            /*val frag =
//                FragmentActivity()?.supportFragmentManager?.findFragmentByTag("android:switcher:" + R.id.idViewpagerData.toString()) as step2Fragment
//            frag.sendArrayListData("switcher send data")
//*/
            //(frag as step2Fragment).sendArrayListData("Hello..!!!!")

//            val summary:SendArrayList=FragmentActivity()?.supportFragmentManager?.findFragmentByTag(R.id.idViewpagerData.toString()) as SendArrayList
//            summary.sendArrayListData("Hello")

//            sm.sendArrayListData("bundlestepper2")
            viewpager?.currentItem = 1
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }





}