package com.example.projectbookbeaconapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectbookbeaconapp.adapters.AutoresAdapter
import com.example.projectbookbeaconapp.adapters.LibrosAdapter
import com.example.projectbookbeaconapp.databinding.FragmentRegister4Binding
import com.example.projectbookbeaconapp.providers.AutoresProvider

class RegisterFragment4 : Fragment() {

    private lateinit var binding: FragmentRegister4Binding //

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegister4Binding.inflate(inflater, container, false) // Se infla el binding
        return binding.root // Se retorna la raíz del binding

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegister4Binding.bind(view)

        binding.imgBack4.setOnClickListener {
            findNavController().navigate(RegisterFragment4Directions.actionFifthFragmentToFourthFragment())
        }

        binding.btBotonSiguiente4.setOnClickListener {
            findNavController().navigate(RegisterFragment4Directions.actionFifthFragmentToSixthFragment())
        }
        initRecyclerView() // Se llama a la función sin pasar la vista
    }

    private fun initRecyclerView() {
        binding.recyclerAutores.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerAutores.adapter = AutoresAdapter(AutoresProvider.autoresList)
    }



}