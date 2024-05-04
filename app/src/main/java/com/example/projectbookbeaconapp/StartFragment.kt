package com.example.projectbookbeaconapp
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.projectbookbeaconapp.databinding.FragmentStartBinding
import com.google.firebase.auth.FirebaseAuth


class StartFragment : Fragment(){
    private lateinit var binding: FragmentStartBinding
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val auth = FirebaseAuth.getInstance()

        // Inflate the layout for this fragment
        binding = FragmentStartBinding.inflate(inflater, container, false) // Se infla el binding
        return binding.root // Se retorna la raíz del binding)



    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStartBinding.bind(view)

        // Encuentra el NavController para el NavHostFragment interno
        val navController = findNavController()

        // Configura el BottomNavigationView para que funcione con el NavController
        binding.bottomNavigation.setupWithNavController(navController)


        replaceFragment(HomeFragment())
        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){ //listener para cada vista con su id
                R.id.home -> replaceFragment(HomeFragment())
                R.id.book -> replaceFragment(BookFragment())
                R.id.profile -> replaceFragment(ProfileFragment())

                else ->{}
            }
            true //devuelve la vista principal por defecto, en este caso Home
        }


    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.navigationStartFragment,fragment)
        fragmentTransaction.commit()
    }

}
