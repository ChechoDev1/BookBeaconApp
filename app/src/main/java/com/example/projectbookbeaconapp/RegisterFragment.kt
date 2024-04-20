package com.example.projectbookbeaconapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class RegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root1 = inflater.inflate(R.layout.fragment_register_1, container, false)

        val btBotonSiguiente = root1.findViewById<Button>(R.id.btBotonSiguiente)
        val imgBack = root1.findViewById<ImageButton>(R.id.imgBack)
        val etRegisterEmail = root1.findViewById<EditText>(R.id.etRegisterEmail)
        val etRegisterName = root1.findViewById<EditText>(R.id.etRegisterName)

        imgBack.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionSecondFragmentToFirstFragment())
        }

        btBotonSiguiente.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionSecondFragmentToThirdFragment(
                nombre = etRegisterName.text.toString(),
                correo = etRegisterEmail.text.toString()))
        }

        //findNavController().navigate(RegisterFragmentDirections.actionSecondFragmentToSixthFragment(
            //correo = etRegisterEmail.text.toString()
            //))

        return root1
    }
}