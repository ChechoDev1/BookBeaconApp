package com.example.projectbookbeaconapp
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectbookbeaconapp.databinding.FragmentHomeBinding
import com.example.projectbookbeaconapp.adapters.LibrosAdapter
import com.example.projectbookbeaconapp.providers.LibrosProvider

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding //


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false) // Se infla el binding
        return binding.root // Se retorna la raíz del binding

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView() // Se llama a la función sin pasar la vista
    }

    private fun initRecyclerView() {
        binding.LibrosView.layoutManager = LinearLayoutManager(requireContext())
        binding.LibrosView.adapter = LibrosAdapter(LibrosProvider.LibrosLista)
    }

}