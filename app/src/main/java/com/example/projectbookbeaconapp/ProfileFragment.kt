package com.example.projectbookbeaconapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.projectbookbeaconapp.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {
    private lateinit var tvUserName: TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        tvUserName = view.findViewById(R.id.tvUserName)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Obtener el UID del usuario actualmente autenticado
        val uid = auth.currentUser?.uid

        // Obtener los datos del usuario desde Firestore
        firestore.collection("users").document(uid!!)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    // Obtener el nombre de usuario del documento
                    val userName = document.getString("usuario")
                    // Establecer el nombre de usuario en el TextView
                    tvUserName.text = userName
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }

        // Configurar el botón de cerrar sesión
        val btnLogout: Button = view.findViewById(R.id.btnLogout)
        btnLogout.setOnClickListener {
            // Aquí deberías manejar la lógica para cerrar sesión
            auth.signOut()
            // Crear un Intent para iniciar MainActivity
            val intent = Intent(activity, MainActivity::class.java)

            // Iniciar MainActivity
            startActivity(intent)

            // Finalizar la actividad actual (opcional)
            activity?.finish()

        }
    }

    companion object {
        private const val TAG = "ProfileFragment"
    }

}