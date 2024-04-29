package com.example.projectbookbeaconapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton

class GenresFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_genres, container, false)
        val imgBack3 = root.findViewById<ImageButton>(R.id.imgBack3)
        val btBotonSiguiente3 = root.findViewById<Button>(R.id.btBotonSiguiente3)


        return root
    }

}