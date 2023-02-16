package com.example.aris4autism_project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aris4autism_project.databinding.FragmentManageNotificationBinding

class ManageNotificationFragment : Fragment() {

    lateinit var binding: FragmentManageNotificationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentManageNotificationBinding.inflate(inflater)

        return binding.root

    }
}