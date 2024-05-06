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
import androidx.activity.OnBackPressedCallback
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

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // No realizar ninguna acción al presionar el botón de retroceso
            }
        }

        // Agregar el callback al controlador de retroceso
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        imgBack.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionSecondFragmentToFirstFragment())
        }

        btBotonSiguiente.setOnClickListener {
            val nombre = etRegisterName.text.toString().trim()
            val correo = etRegisterEmail.text.toString().trim()

            if (nombre.isEmpty()) {
                etRegisterName.error = "Ingrese un nombre"
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                etRegisterEmail.error = "Ingrese un correo válido"
                return@setOnClickListener
            }

            // Si pasa las validaciones, navegar al siguiente fragmento
            findNavController().navigate(RegisterFragmentDirections.actionSecondFragmentToThirdFragment(
                nombre = nombre,
                correo = correo))
        }
        return root1
    }
}