package com.example.projectbookbeaconapp

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectbookbeaconapp.adapters.LibrosAdapter
import com.example.projectbookbeaconapp.databinding.FragmentHomeBinding
import com.example.projectbookbeaconapp.providers.libros_imagenes
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.toObject

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var librosArrayList: ArrayList<libros_imagenes>
    private lateinit var librosAdapter: LibrosAdapter
    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflar el layout utilizando View Binding
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        //Obtener una referencia a LibrosView desde el layout inflado
        val recyclerView = binding.LibrosView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        // Inicializar ArrayList y Adapter
        librosArrayList = arrayListOf()
        librosAdapter = LibrosAdapter(librosArrayList)

        // Configurar el adapter en el RecyclerView
        recyclerView.adapter = librosAdapter
        eventChangeListener()


        return view
    }

    private fun eventChangeListener() {

        firestore = FirebaseFirestore.getInstance()

        firestore.collection("libros_imagenes")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(
                    value: QuerySnapshot?, error: FirebaseFirestoreException?
                ) {
                    if (error != null) {
                        Log.e("Firestore Error", error.message.toString())
                        return
                    }
                    for (dc: DocumentChange in value?.documentChanges!!) {
                        val data = dc.document.data["0"]as Map<*, *>
                        if (dc.type == DocumentChange.Type.ADDED) {
                            // Extrae los atributos que necesitas del mapa
                            val title = data["Title"] as? String ?: ""
                            val genres = data["genres"] as? String ?: ""
                            val author = data["Author"] as? String ?: ""
                            val link = data["Link"] as? String ?: ""

                            val libroImagenes = libros_imagenes(title, genres, author, link)

                            librosArrayList.add(libroImagenes)
                            Log.i("Info", libroImagenes.toString())
                        }
                    break}
                }
            })
    }
}
