package com.example.projectbookbeaconapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController

class RegisterFragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_register_2, container, false)
        val imgBack2 = root.findViewById<ImageButton>(R.id.imgBack2)
        val btBotonSiguiente2 = root.findViewById<Button>(R.id.btBotonSiguiente2)
        imgBack2.setOnClickListener {
            findNavController().navigate(RegisterFragment2Directions.actionThirdFragmentToSecondFragment())
        }

        btBotonSiguiente2.setOnClickListener {
            findNavController().navigate(RegisterFragment2Directions.actionThirdFragmentToFourthFragment())
        }

        return root
    }
}