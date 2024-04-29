package com.example.projectbookbeaconapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterFragment3 : Fragment() {

    private lateinit var auth: FirebaseAuth
    val args:RegisterFragment3Args by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        val nombre = args.nombre
        val correo = args.correo
        val usuario = args.usuario
        val contraseña = args.contrasena

        val tvNombre = view.findViewById<TextView>(R.id.tvNombre)
        tvNombre.text = nombre

        val tvCorreo = view.findViewById<TextView>(R.id.tvCorreo)
        tvCorreo.text = correo

        val tvUsuario = view.findViewById<TextView>(R.id.tvUsuario)
        tvUsuario.text = usuario

        val tvContraseña = view.findViewById<TextView>(R.id.tvContraseña)
        tvContraseña.text = contraseña

       /* auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null)
                }
            }

        */
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_register_3, container, false)

        val btBotonSiguiente6 = root.findViewById<Button>(R.id.btBotonSiguiente6)

        btBotonSiguiente6.setOnClickListener {
            findNavController().navigate(RegisterFragment3Directions.actionFourthFragmentToNavigationFragment())
        }

        return root
    }


}