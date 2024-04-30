package com.example.projectbookbeaconapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.example.projectbookbeaconapp.databinding.FragmentRegister3Binding

class RegisterFragment3 : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentRegister3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegister3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        // Obtener los argumentos pasados desde el fragmento anterior
        val args: RegisterFragment3Args by navArgs()
        val nombre = args.nombre
        val correo = args.correo
        val usuario = args.usuario
        val contraseña = args.contrasena

        // Configurar el texto de los TextView con los datos del usuario
        /*
        binding.tvNombre.text = nombre
        binding.tvCorreo.text = correo
        binding.tvUsuario.text = usuario
        binding.tvContraseña.text = contraseña
         */

        // Manejar el evento de clic en el botón de registro
        binding.btBotonSiguiente6.setOnClickListener {
            // Obtener el email y la contraseña ingresados por el usuario
            val email = correo // Utilizamos el correo obtenido anteriormente
            val password = contraseña // Utilizamos la contraseña obtenida anteriormente

            // Crear el usuario con email y contraseña en Firebase Authentication
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Registro exitoso, navegar a la siguiente pantalla
                        Log.d(TAG, "createUserWithEmail:success")

                        val db = FirebaseFirestore.getInstance()


                        findNavController().navigate(RegisterFragment3Directions.actionFourthFragmentToNavigationFragment())
                    } else {
                        // Fallo en el registro, mostrar un mensaje de error
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            requireContext(), "Error al crear la cuenta: ${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
    companion object {
        private const val TAG = "RegisterFragment3"
    }
}