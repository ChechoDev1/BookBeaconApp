package com.example.projectbookbeaconapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        val btBotonRegister = root.findViewById<Button>(R.id.btRegister)
        val btBotonLogin = root.findViewById<Button>(R.id.btBotonLogin)
        btBotonRegister.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionFirstFragmentToSecondFragment())
        }
        btBotonLogin.setOnClickListener{
            findNavController().navigate(MainFragmentDirections.actionFirstFragmentToStartFragment())
        }

        return root
    }
}