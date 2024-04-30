package com.example.projectbookbeaconapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
//import com.example.projectbookbeaconapp.databinding.ActivityMainBinding
import com.example.projectbookbeaconapp.databinding.FragmentBookBinding
import com.example.projectbookbeaconapp.databinding.FragmentNavigationBinding

class NavigationFragment : Fragment() {
    private lateinit var binding2 : FragmentBookBinding
    private lateinit var binding : FragmentNavigationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavigationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar el BottomNavigationView para navegar entre fragmentos
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    // Navegar al fragmento Home
                    navigateToFragment(HomeFragment())
                    true
                }
                R.id.book -> {
                    // Navegar al fragmento Book
                    navigateToFragment(BookFragment())
                    true
                }
                R.id.person -> {
                    // Navegar al fragmento Profile
                    navigateToFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }
}