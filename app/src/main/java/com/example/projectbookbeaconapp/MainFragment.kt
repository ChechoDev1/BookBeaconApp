package com.example.projectbookbeaconapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.projectbookbeaconapp.databinding.FragmentLoginBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false) // Se infla el binding
        return binding.root // Se retorna la raíz del binding)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Encuentra el NavController
        val navController = findNavController()

        // Configura un OnClickListener para tu botón de inicio de sesión
        binding.btBotonLogin.setOnClickListener {
            // Navega a tu próximo fragmento
            navController.navigate(R.id.action_firstFragment_to_startFragment)
        }
        binding.btRegister.setOnClickListener {
            // Navega a tu próximo fragmento
            navController.navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }

}