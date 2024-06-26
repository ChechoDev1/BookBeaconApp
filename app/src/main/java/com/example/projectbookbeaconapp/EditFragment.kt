package com.example.projectbookbeaconapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.projectbookbeaconapp.databinding.FragmentEditBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EditFragment : Fragment() {
    private lateinit var binding: FragmentEditBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var uid: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditBinding.inflate(inflater,container,false)

        // Inicializar FirebaseAuth
        auth = FirebaseAuth.getInstance()

        firestore = FirebaseFirestore.getInstance()

        // Obtener uid
        uid = auth.currentUser?.uid ?: ""

        mostrarDatosUsuario()

        binding.actualizarDatos.setOnClickListener{
            actualizarDatosUsuario()
        }

        return binding.root
    }

    private fun actualizarDatosUsuario() {
        val nuevoUsuario = binding.actualizarUsuario.text.toString().trim()
        val nuevoNombre = binding.actualizarNombre.text.toString().trim()

        // Verificar si los campos de usuario y nombre no están vacíos
        if (nuevoUsuario.isEmpty() || nuevoNombre.isEmpty()) {
            // Mostrar un mensaje de error si los campos están vacíos
            showAlert("Por favor, ingresa un usuario y un nombre válidos")
            return
        }

        // Si los campos no están vacíos, actualizar los datos del usuario
        val userData: MutableMap<String, Any> = hashMapOf(
            "usuario" to nuevoUsuario,
            "nombre" to nuevoNombre
        )
        uid = auth.currentUser?.uid ?: ""
        firestore.collection("users").document(uid)
            .update(userData)
            .addOnSuccessListener {
                showAlert("Datos actualizados correctamente\nEs posible que no se puedan ver los cambios realizados al instante")
                Log.d("update", "Todo correcto")
            }
            .addOnFailureListener { e ->
                Log.e("update","Algo salio mal")
            }
    }

    private fun mostrarDatosUsuario() {
        // Obtener la referencia al documento de usuario en Firestore
        firestore.collection("users").document(uid).get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    // Obtener los datos del documento y mostrarlos en los EditText
                    val usuario = document.getString("usuario")
                    val nombre = document.getString("nombre")
                    binding.actualizarUsuario.setText(usuario)
                    binding.actualizarNombre.setText(nombre)
                }
            }
            .addOnFailureListener { e ->
                Log.e("mostrarDatosUsuario", "Error al obtener datos del usuario", e)
            }
    }

    private fun showAlert(message: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setPositiveButton("Aceptar", null)
            .show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgBack4.setOnClickListener{
            findNavController().navigate(EditFragmentDirections.actionEditFragmentToNavigationFragment())
        }


    }

}