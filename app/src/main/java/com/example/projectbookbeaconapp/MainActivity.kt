package com.example.projectbookbeaconapp
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projectbookbeaconapp.databinding.ActivityMainBinding
import com.example.projectbookbeaconapp.providers.LibrosProvider


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        LibrosProvider.cargarLibrosDesdeCSV(this)

    }

}
