package com.example.aris4autism_project.stepperdemo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.aris4autism_project.R
import com.example.aris4autism_project.databinding.FragmentStep2Binding

class step2Fragment : Fragment(),SendArrayList {

    lateinit var binding:FragmentStep2Binding
    var bundle=Bundle()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding=FragmentStep2Binding.inflate(inflater)

        if(arguments?.getString("username")!=null)
        {
            var strUserName: String = arguments?.getString("username").toString()
            Log.e("your username is =", strUserName.toString())
            binding.step2txt.text = strUserName
        }

        val viewpager=activity?.findViewById<ViewPager2>(R.id.idViewpagerData)
        binding.btnStep2.setOnClickListener {

            var fragment3=step3Fragment()
            var bundle=Bundle()
            bundle.putString("usernameStep3",binding.edtxSecond.text.toString())
            fragment3.arguments=bundle
            activity?.supportFragmentManager?.beginTransaction()?.add(R.id.framelayout3,fragment3)?.commit()

            viewpager?.currentItem=2
        }

        return binding.root
    }

    fun displayArrayList(message:String)
    {
        Log.e("displayArrayListData=",message.toString())
    }

        fun newInstance(parameter: String?): step2Fragment {
            val args = Bundle()
            Log.e("parameter=", parameter.toString())
            args.putString("parameter", parameter)
            val fragment = step2Fragment()
            fragment.setArguments(args)
            return fragment
        }

    override fun sendArrayListData(sm: String) {
        Log.e("sendArrayData=",sm.toString())
    }


}