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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectbookbeaconapp.adapter.SavedBooksAdapter
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
        }

        if (uid != null) {
            // Obtener la referencia a la colección "usuarios" y al documento con el UID del usuario actual
            val userRef = firestore.collection("usuarios").document(uid)

            // Obtener la referencia a la subcolección "libros" del documento del usuario actual
            val librosRef = userRef.collection("libros")

            // Configurar un Listener para escuchar los cambios en la colección "libros"
            librosRef.addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    // Manejar cualquier error que pueda ocurrir al obtener los libros
                    Log.e(TAG, "Error al obtener los libros: ", exception)
                    return@addSnapshotListener
                }

                // Verificar si el snapshot no es nulo y contiene documentos
                if (snapshot != null && !snapshot.isEmpty) {
                    // Crear una lista mutable para almacenar los libros del usuario
                    val userBooks = mutableListOf<UserBook>()

                    // Iterar sobre los documentos en el snapshot
                    for (doc in snapshot.documents) {
                        // Obtener los datos del documento y crear un objeto UserBook
                        val title = doc.getString("title") ?: ""
                        val author = doc.getString("author") ?: ""
                        val genres = doc.getString("genres") ?: ""

                        val userBook = UserBook(title, author, genres)

                        // Agregar el libro a la lista
                        userBooks.add(userBook)
                    }

                    // Mostrar los libros en el RecyclerView utilizando el adaptador
                    val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewSavedBooks)
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = SavedBooksAdapter(userBooks)
                } else {
                    // Si no hay libros guardados, puedes mostrar un mensaje al usuario
                    Log.d(TAG, "No se encontraron libros guardados")
                }
            }
        }

    }

    companion object {
        private const val TAG = "ProfileFragment"
    }

}