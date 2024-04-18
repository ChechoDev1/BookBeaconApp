package com.example.projectbookbeaconapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController

class RegisterFragment5 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_register_5, container, false)
        val imgBack5 = root.findViewById<ImageButton>(R.id.imgBack5)
        val btBotonSiguiente5 = root.findViewById<Button>(R.id.btBotonSiguiente5)
        imgBack5.setOnClickListener {
            findNavController().navigate(RegisterFragment5Directions.actionSixthFragmentToFifthFragment())
        }

        btBotonSiguiente5.setOnClickListener {
            //findNavController().navigate(RegisterFragment5Directions.actionSixthFragmentToSeventhFragment())
            findNavController().navigate(RegisterFragment5Directions.actionSixthFragmentToStartFragment())
        }

        return root
    }
}