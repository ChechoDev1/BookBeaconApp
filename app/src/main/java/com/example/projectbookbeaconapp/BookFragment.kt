package com.example.projectbookbeaconapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.projectbookbeaconapp.databinding.FragmentBookBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookFragment : Fragment() {
    private var _binding: FragmentBookBinding? = null
    private val binding get() = _binding!!
    private lateinit var userId: String
    private lateinit var firestore: FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookBinding.inflate(inflater, container, false) // Inflar el layout con el binding
        return binding.root // Devolver la raíz del layout inflado
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar Firebase Firestore
        firestore = FirebaseFirestore.getInstance()
        // Obtener el ID del usuario actual
        userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

        val retrofit = Retrofit.Builder()
            .baseUrl("https://bookbeaconapp-api.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(BookApiService::class.java)

        val call = service.getRecommendations(userId)
        call.enqueue(object : Callback<List<BookRecommendation>> {
            override fun onResponse(call: Call<List<BookRecommendation>>, response: Response<List<BookRecommendation>>) {
                if (response.isSuccessful) {
                    val recommendations = response.body()
                    Log.i("recomendacion","todo bien!")
                } else {
                    // Manejar el error
                }
            }

            override fun onFailure(call: Call<List<BookRecommendation>>, t: Throwable) {
                // Manejar el fallo de la solicitud
            }
        })

        binding.btGeneros.setOnClickListener {
            findNavController().navigate(BookFragmentDirections.actionBookFragmentToGenresFragment())
        }
        binding.btAutores.setOnClickListener {
            findNavController().navigate(BookFragmentDirections.actionBookFragmentToAuthorsFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Liberar la referencia al binding para evitar memory leaks
    }

}