package com.example.projectbookbeaconapp

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectbookbeaconapp.adapters.SavedBooksAdapter
import com.example.projectbookbeaconapp.databinding.FragmentProfileBinding
import com.example.projectbookbeaconapp.providers.UserBook
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val imgEdit: ImageView = view.findViewById(R.id.imgEdit)

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
                    binding.tvUserName.text = userName
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }

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


        imgEdit.setOnClickListener {
            // Obtener el NavController de la actividad principal
            val navController = requireActivity().findNavController(R.id.navigationStartFragment)

            // Reemplazar el fragmento actual con el nuevo fragmento
            navController.navigate(R.id.editFragment)
        }


        binding.btActualizarLibros.setOnClickListener{
            if (uid != null) {
                // Obtener la referencia a la colección "usuarios" y al documento con el UID del usuario actual
                val userRef = firestore.collection("usuarios").document(uid)

                // Obtener la referencia a la subcolección "libros" del documento del usuario actual
                val librosRef = userRef.collection("libros")

                // Configurar un Listener para escuchar los cambios en la colección "libros"
                librosRef.addSnapshotListener { snapshot, exception ->
                    if (exception != null) {
                        // Manejar cualquier error que pueda ocurrir al obtener los libros
                        Log.e(ContentValues.TAG, "Error al obtener los libros: ", exception)
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
                        //recyclerView.layoutManager = LinearLayoutManager(requireContext())
                        if (isAdded) {
                            recyclerView.layoutManager = LinearLayoutManager(requireContext())
                        }
                        recyclerView.adapter = SavedBooksAdapter(userBooks)
                    } else {
                        // Si no hay libros guardados
                        Log.d(ContentValues.TAG, "No se encontraron libros guardados")
                    }
                }
            }
        }
    }

}