package com.example.projectbookbeaconapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.projectbookbeaconapp.databinding.FragmentAuthorsBinding

class AuthorsFragment : Fragment() {

    private lateinit var binding: FragmentAuthorsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAuthorsBinding.inflate(inflater, container, false) // Se infla el binding



        return binding.root // Se retorna la raíz del binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the click listeners for the buttons
        binding.imgBack4.setOnClickListener {
            findNavController().navigate(AuthorsFragmentDirections.actionAuthorsFragmentToNavigationFragment())
        }

    }

}
