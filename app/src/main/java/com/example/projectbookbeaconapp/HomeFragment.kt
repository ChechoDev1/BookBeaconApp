package com.example.projectbookbeaconapp

import android.annotation.SuppressLint
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

        // Obtener una referencia a LibrosView desde el layout inflado
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

    private fun eventChangeListener(){

        firestore = FirebaseFirestore.getInstance()

        // Prueba de lectura de un documento específico
        firestore.collection("Libros_imagenes").document("10")
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d("Firestore Success", "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d("Firestore Error", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Firestore Error", "get failed with ", exception)
            }


        firestore.collection("Libros_imagenes").addSnapshotListener(object : EventListener<QuerySnapshot>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onEvent(
                value: QuerySnapshot?, error: FirebaseFirestoreException?){
                if (error != null){
                    Log.e("Firestore Error",error.message.toString())
                    return
                }
                for (dc : DocumentChange in value?.documentChanges!!){
                    if(dc.type == DocumentChange.Type.ADDED){
                        librosArrayList.add(dc.document.toObject(libros_imagenes::class.java))
                    }
                }

                librosAdapter.notifyDataSetChanged()

            }
        })

    }

}
