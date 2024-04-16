package com.example.projectbookbeaconapp
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.projectbookbeaconapp.databinding.FragmentInicioBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : FragmentInicioBinding
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){ //listener para cada vista con su id
                R.id.home -> replaceFragment(HomeFragment())
                R.id.match -> replaceFragment(MatchFragment())
                R.id.profile -> replaceFragment(ProfileFragment())

                else ->{}
            }
            true //devuelve la vista principal por defecto, en este caso Home
        }

    }

    //Función que cambia los fragmentos del button navigation view (Home, Profile, Match)
    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_inicio,fragment)
        fragmentTransaction.commit()
    }
}