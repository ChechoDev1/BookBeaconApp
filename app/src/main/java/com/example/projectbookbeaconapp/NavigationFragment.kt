package com.example.projectbookbeaconapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectbookbeaconapp.databinding.ActivityMainBinding
import com.example.projectbookbeaconapp.databinding.FragmentNavigationBinding

class NavigationFragment : Fragment() {
    private lateinit var binding : FragmentNavigationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavigationBinding.inflate(inflater, container, false)

        //binding.bottomNavigationView {

        //}

        return binding.root
    }

    private fun replaceFragment(fragment: Fragment) {
        //val fragmentManager = supportFragmentManager

    }
}